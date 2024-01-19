const TokenManager = {
    getAccessToken: () => sessionStorage.getItem("accessToken"),
    getClaims: () => {
        const token = sessionStorage.getItem("accessToken");
        if (!token) {
            return undefined;
        }

        const [, payloadBase64] = token.split('.');
        if (!payloadBase64) {
            return undefined;
        }

        try {
            const payloadJson = atob(payloadBase64);
            const claims = JSON.parse(payloadJson);
            return claims;
        } catch (error) {
            console.error('Error decoding JWT payload:', error);
            return undefined;
        }
    },
    setAccessToken: (token) => {
        sessionStorage.setItem("accessToken", token);
        const claims = TokenManager.getClaims();
        sessionStorage.setItem("claims", JSON.stringify(claims));
        return claims;
    },
    clear: () => {
        sessionStorage.removeItem("accessToken");
        sessionStorage.removeItem("claims");
    }
}

export default TokenManager;