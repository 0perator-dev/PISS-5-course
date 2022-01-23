import { NgModule } from "@angular/core";
import { PreloadAllModules, RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { CanActivateDashboardGuard } from "./guards/can-activate-dashboard.guard";

const routes: Routes = [
    // { path: "", redirectTo: "dash", pathMatch: "full" },
    { path: "login", component: LoginComponent },
    { path: "register", component: LoginComponent },
    {
        path: "",
        loadChildren: () => import("./components/components.module").then(m => m.ComponentsModule),
        canActivate: [CanActivateDashboardGuard]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
