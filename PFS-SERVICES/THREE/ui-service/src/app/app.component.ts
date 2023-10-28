import { Component } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { AuthService } from './Auth-Service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ui-service';

  constructor(
    private toast: NgToastService, private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.showSuccess();
  }

  showSuccess() {
    this.toast.success({detail:"SUCCESS",summary:'Your Success Message',duration:5000});
  }

  showSuccessTopCenter() {
    this.toast.success({detail:"SUCCESS",summary:'Your Success Message',duration:5000, position:'topCenter'});
  }
  
  showError() {
    this.toast.error({detail:"ERROR",summary:'Your Error Message',sticky:true});
  }

  showInfo() {
    this.toast.info({detail:"INFO",summary:'Your Info Message',sticky:true});
  }

  isLoggedIn(): boolean {
    const token = this.authService.getToken();
    return !!token; 
  }

  logout() {
    this.authService.removeToken();
  }

  
}
