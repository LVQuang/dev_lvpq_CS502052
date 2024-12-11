import { BrandComponents } from '/js/Components/BrandComponents.js';

document.addEventListener('DOMContentLoaded', () => {
    new BrandComponents({
        input: "brand-logo",
        name: "brand-logo-file-name",
        button: "brand-logo-btn"
    });
    new BrandComponents({
        input: "brand-contract",
        name: "brand-contract-file-name",
        button: "brand-contract-btn"
    });
});