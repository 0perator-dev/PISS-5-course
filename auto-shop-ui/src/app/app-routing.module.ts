import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { CanActivateDashboardGuard } from "./guards/can-activate-dashboard.guard";

const routes: Routes = [
    { path: "login", component: LoginComponent },
    {
        path: "",
        loadChildren: () => import("./components/components.module").then(m => m.ComponentsModule),
        canActivate: [CanActivateDashboardGuard]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
