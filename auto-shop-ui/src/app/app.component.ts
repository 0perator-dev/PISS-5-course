import { Component, ElementRef } from "@angular/core";
import { AlertItem } from "./types/alert-item";
import { NavGroup } from "./types/nav-group";
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.scss"]
})
export class AppComponent {
    public alerts: AlertItem[] = [];
    public sideNavGroups: NavGroup[] = [];

    constructor() {
    }
}
