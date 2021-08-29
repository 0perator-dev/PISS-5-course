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

    constructor(private httpClient: HttpClient,) {
        const headers = new HttpHeaders().set("Content-Type", "text/html; charset=utf-8").set("Accept", "text/html; charset=utf-8");

        const httpOptions = {
            headers: new HttpHeaders({
                "Accept": "text/html",
                "Content-Type": "application/json"
            }),
            responseType: "text"
        };

        httpClient.get("http://localhost:8080/login", {observe: "response" ,responseType: "text"})
            .subscribe((response) => {
                const a = document.getElementsByTagName("body");
                const b = document.createElement("html");
                b.innerHTML = response.body as string;

                const links = b.getElementsByTagName("link");
                for (const link of Array.from(links)) {
                    const newHref = "http://localhost:8080" + link.getAttribute("href");
                    link.setAttribute("href", newHref);
                    document.getElementsByTagName("head")[0].appendChild(link);
                }

                a[0].innerHTML = b.getElementsByTagName("body")[0].innerHTML;

            }, (error) => {
                console.log(error)
            });
    }
}
