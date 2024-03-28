import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private http: HttpClient, private router: Router) { }
  

  login() {
    this.http.post<any>('http://localhost:8080/Person/login', { email: this.email, password: this.password })
      .subscribe(
        response => {
          // หากเข้าสู่ระบบสำเร็จ รับ JWT token จาก response แล้วทำตามต้องการ
          const jwtToken = response.token;
          // ทำการจัดเก็บ token ใน local storage เพื่อใช้ในการยืนยันตัวตนในแอปพลิเคชัน
          localStorage.setItem('jwtToken', jwtToken);
          this.router.navigate(['/home']);
        },
        error => {
          // กรณีเกิดข้อผิดพลาดในการเข้าสู่ระบบ
          this.errorMessage = 'เข้าสู่ระบบล้มเหลว กรุณาตรวจสอบอีเมลและรหัสผ่านของคุณ';
        }
      );
  }
  
}

