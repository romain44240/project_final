export class AuthRequest {
    constructor(private _login: string, private _password: string, private _nom?: string, private _prenom?: string, private _telephone?: string, private _email?: string){}

    public get login(): string {
        return this._login;
    }

    public set login(value: string) {
        this._login = value;
    }

    public get password(): string {
        return this._password;
    }

    public set password(value: string) {
        this._password = value;
    }

    public get nom(): string | undefined {
        return this._nom;
    }

    public set nom(value: string) {
        this._nom = value;
    }

    public get prenom(): string | undefined{
        return this._prenom;
    }

    public set prenom(value: string) {
        this._prenom = value;
    }

    public get telephone(): string | undefined{
        return this._telephone;
    }

    public set telephone(value: string) {
        this._telephone = value;
    }

    public get email(): string | undefined{
        return this._email;
    }

    public set email(value: string) {
        this._email = value;
    }


}
