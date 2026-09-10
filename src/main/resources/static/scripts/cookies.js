document.addEventListener("DOMContentLoaded", function () {

    const banner = document.getElementById("cookie-banner");
    const acceptButton = document.getElementById("cookie-accept");
    const rejectButton = document.getElementById("cookie-reject");

    const cookieConsent = localStorage.getItem("cookieConsent");

    if (cookieConsent) {
        banner.style.display = "none";

        if (cookieConsent === "accepted") {
            loadGoogleAnalytics();
        }

        return;
    }

    banner.style.display = "block";


    acceptButton.addEventListener("click", function () {

        localStorage.setItem("cookieConsent", "accepted");

        banner.style.display = "none";

        loadGoogleAnalytics();
    });


    rejectButton.addEventListener("click", function () {

        localStorage.setItem("cookieConsent", "rejected");

        banner.style.display = "none";
    });
});


function loadGoogleAnalytics() {

    // Nie ładuj Analytics drugi raz
    if (document.getElementById("google-analytics-script")) {
        return;
    }

    const script = document.createElement("script");

    script.id = "google-analytics-script";
    script.async = true;
    script.src = "https://www.googletagmanager.com/gtag/js?id=G-DH7K747WYG";

    document.head.appendChild(script);


    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    window.gtag = gtag;

    gtag("js", new Date());

    gtag("config", "G-DH7K747WYG");
}