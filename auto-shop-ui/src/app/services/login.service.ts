import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from "@angular/common/http";
import { ServerUrl } from "./http.service";
import { LoggedUser } from "../types/user/logged-user";
import { tap } from "rxjs/operators";
import { UserToRegister } from "../types/user/user-to-register";

const LOGIN_ENDPOINT = ServerUrl + '/api/auth/login';
const REGISTER_ENDPOINT = ServerUrl + '/api/auth/register';
const JWT_TOKEN = 'JWT_TOKEN';
export const FrontEndUrl = "http://localhost:4200"

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private http: HttpClient) {
    }

    public login(username: string, password: string) {
        return this.http.post<LoggedUser>(LOGIN_ENDPOINT, {username, password}, {observe: "response"})
            .pipe(tap(this.setSession));
    }

    public logout() {
        localStorage.removeItem(JWT_TOKEN);
    }

    public register(user: UserToRegister) {
        return this.http.post<LoggedUser>(REGISTER_ENDPOINT, user, {observe: "response"})
            .pipe(tap(this.setSession));
    }

    public isUserLogged(): boolean {
        return !!localStorage.getItem(JWT_TOKEN);
    }

    private setSession(response: HttpResponse<LoggedUser>) {
        if(!response.body){
            return;
        }

        localStorage.setItem(JWT_TOKEN, response.body.token);
    }
}
