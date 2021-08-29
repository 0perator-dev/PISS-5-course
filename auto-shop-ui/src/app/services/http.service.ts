import { Injectable } from "@angular/core";
import { HttpClient, HttpEvent, HttpHeaders, HttpRequest, HttpResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { filter } from "rxjs/operators";

export const ServerUrl = "http://localhost:8080";

export enum Methods {
    GET = "GET",
    POST = "POST",
    PUT = "PUT",
    DELETE = "DELETE"
}

export const ApiEndpoints = {
    AnnouncementsList: {
        url: ServerUrl + "/api/announcements",
        method: Methods.GET
    }
}

@Injectable({ providedIn: "root" })
export class HttpService {

    private url = ServerUrl;

    constructor(private httpClient: HttpClient) {
    }

    performRequest<T>(request: HttpRequest<any>): Observable<HttpResponse<T>> {
        const bearer = window.localStorage.getItem("Authentication");
        if(bearer !== null) {
            request.headers.append("Authentication", bearer)
        }

        // @ts-ignore
        return this.httpClient.request<T>(request);
    }

    isHttpRequest<T>(response: HttpEvent<T>): response is HttpResponse<T> {
        return response instanceof HttpResponse;
    }

    getLoginView() {
        this.httpClient.get(`${this.url}/login`, { observe: "response", responseType: "text" })
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

                const formTag = htmlElement.getElementsByTagName("form")[0];
                const submitLink = formTag.getAttribute("action");
                formTag.setAttribute("action", "http://localhost:8080" + submitLink);

                bodyElements[0].innerHTML = htmlElement.getElementsByTagName("body")[0].innerHTML;
            }, (error) => {

            });
    }

    getRegistrationView() {
        this.httpClient.get(`${this.url}/registration`, { observe: "response", responseType: "text" })
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

                const formTag = htmlElement.getElementsByTagName("form")[0];
                const submitLink = formTag.getAttribute("action");
                formTag.setAttribute("action", "http://localhost:8080" + submitLink);

                bodyElements[0].innerHTML = htmlElement.getElementsByTagName("body")[0].innerHTML;
            }, (error) => {

            });
    }

}
