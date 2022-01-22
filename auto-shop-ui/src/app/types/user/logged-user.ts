import { AuthenticatedUser } from "./authenticated-user";

export interface LoggedUser {
    token: string;
    authenticatedUser: AuthenticatedUser;
}
