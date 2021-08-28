import { Component } from "@angular/core";
import { AlertItem } from "./types/alert-item";
import { NavGroup } from "./types/nav-group";

@Component({
    selector: "app-root",
    templateUrl: "./app.component.html",
    styleUrls: ["./app.component.scss"]
})
export class AppComponent {
    public alerts: AlertItem[] = [];
    public sideNavGroups: NavGroup[] = [];
}
