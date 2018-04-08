class Auth0Config {
    
    domain() {
        return window.config.auth0.domain;
    }

    clientID() {
        return window.config.auth0.clientID;
    }

    redirectUri() {
        return `${window.config.auth0.redirectUriBase}/callback`;
    }

    audience() {
        return  window.config.auth0.audience;
    }
}

const auth0Config = new Auth0Config();

export default auth0Config;