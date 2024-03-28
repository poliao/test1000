import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  constructor(private router: Router) { }
gotoprofile(){
  window.location.href = '/profile';
}
decodedToken: any;

  ngOnInit(): void {
    // Decode the JWT token
    const token = localStorage.getItem('jwtToken');
    if (token) {
      this.decodedToken = jwtDecode(token);
    } else {
      this.router.navigate(['/login']);
    }
  }
}
function jwtDecode(token: string): any {
  throw new Error('Function not implemented.');
}

