import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from "../services/login.service";

@Injectable({
    providedIn: 'root'
})
export class CanActivateDashboardGuard implements CanActivate {

    constructor(private loginService: LoginService, private router: Router) {
    }


    canActivate(): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        if (this.loginService.isUserLogged()) {
            return true;
        }

        return this.router.createUrlTree(["/login"]);
    }

}
