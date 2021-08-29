import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({providedIn: 'root'})
export class HttpService {

    private url = 'http://localhost:8080';

    constructor(private httpClient: HttpClient) {}

    getLoginView() {
        this.httpClient.get(`${this.url}/login`, {observe: "response" ,responseType: "text"})
            .subscribe((response) => {

                const bodyElements = document.getElementsByTagName("body");
                const htmlElement = document.createElement("html");
                htmlElement.innerHTML = response.body as string;

                const links = htmlElement.getElementsByTagName("link");
                for (const link of Array.from(links)) {
                    const newHref = "http://localhost:8080" + link.getAttribute("href");
                    link.setAttribute("href", newHref);
                    document.getElementsByTagName("head")[0].appendChild(link);
                }

                bodyElements[0].innerHTML = htmlElement.getElementsByTagName("body")[0].innerHTML;
            }, (error) => {

            });
    }

    getRegistrationView() {
        this.httpClient.get(`${this.url}/registration`, {observe: "response" ,responseType: "text"})
            .subscribe((response) => {

                const bodyElements = document.getElementsByTagName("body");
                const htmlElement = document.createElement("html");
                htmlElement.innerHTML = response.body as string;

                const links = htmlElement.getElementsByTagName("link");
                for (const link of Array.from(links)) {
                    const newHref = "http://localhost:8080" + link.getAttribute("href");
                    link.setAttribute("href", newHref);
                    document.getElementsByTagName("head")[0].appendChild(link);
                }

                bodyElements[0].innerHTML = htmlElement.getElementsByTagName("body")[0].innerHTML;
            }, (error) => {

            });
    }

}
