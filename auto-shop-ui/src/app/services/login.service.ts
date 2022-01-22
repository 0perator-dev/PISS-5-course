import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from "@angular/common/http";
import { ServerUrl } from "./http.service";
import { LoggedUser } from "../types/user/logged-user";
import { tap } from "rxjs/operators";

const LOGIN_ENDPOINT = ServerUrl + '/api/auth/login';
const JWT_TOKEN = 'JWT_TOKEN';

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
