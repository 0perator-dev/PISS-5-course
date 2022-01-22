import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { LoginService } from "../services/login.service";
import { catchError } from "rxjs/operators";
import { of } from "rxjs";
import { HttpErrorResponse } from "@angular/common/http";

const FormControls = {
    username: "username",
    password: "password"
}

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    formGroup: FormGroup;
    FormControls = FormControls;
    errorMessage: string = '';

    constructor(private formBuilder: FormBuilder, private loginService: LoginService) {
        this.formGroup = this.formBuilder.group({
            [FormControls.username]: ['', Validators.required],
            [FormControls.password]: ['', Validators.required]
        });
    }

    ngOnInit(): void {
    }

    public login() {
        this.loginService.login(
            this.formGroup.controls[FormControls.username].value,
            this.formGroup.controls[FormControls.password].value
        )
            .pipe(catchError((error: HttpErrorResponse) => {
                this.errorMessage = error.message;
                return of();
            }))
            .subscribe();
    }

}
