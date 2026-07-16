document.addEventListener("DOMContentLoaded", () => {
    const offcanvas = document.getElementById("offcanvasNavbar");
    const menuButton = document.querySelector(".menu");

    if (!offcanvas || !menuButton) return;

    offcanvas.addEventListener("show.bs.offcanvas", () => {
        menuButton.classList.add("displaynone");
    });

    offcanvas.addEventListener("hide.bs.offcanvas", () => {
        menuButton.classList.remove("displaynone");
    });
});