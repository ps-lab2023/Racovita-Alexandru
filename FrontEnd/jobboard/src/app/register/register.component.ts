import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthserviceService} from '../services/authservice.service';
import {User} from "../models/user.model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  user: User = new User();
  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthserviceService,

    private userService: UserService,
    private router: Router
  ) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      recaptcha: [null, Validators.required],
    });
  }

  ngOnInit(): void {}

 /* onRegister(): void {
    console.log('onRegister called'); // Debugging: check if the function is called
    if (this.registerForm.valid) {
      console.log('Form is valid'); // Debugging: check if the form is valid
      const { username, email, password } = this.registerForm.value;
      this.user.username = username;
      this.user.email = email;
      this.user.password = password
      this.user.role = "USER";
      this.userService.register(this.user).subscribe(
        () => {
          this.router.navigate(['/login']);
        },
        (error) => {
          console.error('Registration failed:', error);
        }
      );
    } else {
      console.log('Form is invalid'); // Debugging: check if the form is invalid
    }
  }
*/
  onRegister(): void {
    console.log('onRegister called');
    if (this.registerForm.valid) {
      console.log('Form is valid');
      const { username, email, password, recaptcha } = this.registerForm.value;
      this.user.username = username;
      this.user.email = email;
      this.user.password = password;
      this.user.role = 'USER';
      this.userService.register(this.user).subscribe(
        () => {
          this.router.navigate(['/login']);
        },
        (error) => {
          console.error('Registration failed:', error);
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }

}
