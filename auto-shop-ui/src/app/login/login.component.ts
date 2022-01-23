import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { LoginService } from "../services/login.service";
import { catchError } from "rxjs/operators";
import { of } from "rxjs";
import { HttpErrorResponse } from "@angular/common/http";
import { Router } from "@angular/router";
import { UserToRegister } from "../types/user/user-to-register";
import { FrontEndUrl, ServerUrl } from "../resources/application.properties";

const FormControls = {
    username: "username",
    password: "password",
    name: "name",
    role: "role"
}

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent {
    formGroup: FormGroup;
    FormControls = FormControls;
    errorMessage: string = '';
    isRegisterPage: boolean = false;

    constructor(
        private formBuilder: FormBuilder,
        private loginService: LoginService,
        private router: Router,
    ) {
        console.log(this.router);
        this.isRegisterPage = this.router.url.indexOf("register") > -1;

        this.formGroup = this.isRegisterPage ? this.genFormGroupForRegister() : this.genFormGroupForLogin();
    }

    private genFormGroupForLogin(): FormGroup {
        return this.formBuilder.group({
            [FormControls.username]: ['', Validators.required],
            [FormControls.password]: ['', Validators.required]
        });
    }

    private genFormGroupForRegister(): FormGroup {
        return this.formBuilder.group({
            [FormControls.username]: ['', Validators.required],
            [FormControls.name]: ['', Validators.required],
            [FormControls.role]: ['', Validators.required],
            [FormControls.password]: ['', Validators.required]
        });
    }

    public submit() {
        if (this.isRegisterPage) {
            this.register();
        } else {
            this.login();
        }
    }

    public loginWithGoogle() {
        window.location.href = `
            ${ServerUrl}/oauth2/authorization/google?response_type=code&scope=openid%20write%20read
        `
            // + "&client_id=" + this.loginService.clientId
            + '&redirect_uri='+ FrontEndUrl;
    }

    private login() {
        this.loginService.login(
            this.formGroup.controls[FormControls.username].value,
            this.formGroup.controls[FormControls.password].value
        )
            .pipe(catchError((error: HttpErrorResponse) => {
                this.errorMessage = error.message;
                return of();
            }))
            .subscribe(() => this.onSuccess());
    }

    private register() {
        const userToRegister: UserToRegister = {
            username: this.formGroup.controls[FormControls.username].value,
            password: this.formGroup.controls[FormControls.password].value,
            name: this.formGroup.controls[FormControls.name].value,
            roles: [this.formGroup.controls[FormControls.role].value]
        }

        this.loginService.register(userToRegister)
            .pipe(catchError((error: HttpErrorResponse) => {
                this.errorMessage = error.message;
                return of();
            }))
            .subscribe(() => this.onSuccess())
    }

    private onSuccess() {
        console.log("1")
        this.router.navigate(["/"], );
    }

}
