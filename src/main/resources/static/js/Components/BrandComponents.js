export class BrandComponents {
    fileInput;
    fileName;
    uploadBtn;

    constructor(parameters) {
        this.fileInput = document.getElementById(parameters.input);
        this.fileName = document.getElementById(parameters.name);
        this.uploadBtn = document.getElementById(parameters.button);

        if (!this.fileInput || !this.fileName || !this.uploadBtn) {
            console.error("One or more DOM elements not found:", parameters);
            return;
        }

        this.initEvents();
    }

    initEvents() {
        this.uploadBtn.addEventListener("click", () => this.handleUploadClick());
        this.fileInput.addEventListener("change", () => this.handleFileChange());
    }

    handleUploadClick() {
        this.fileInput.click();
    }

    handleFileChange() {
        this.fileName.textContent = this.fileInput.files[0]
            ? this.fileInput.files[0].name
            : "No file chosen";
    }
}
