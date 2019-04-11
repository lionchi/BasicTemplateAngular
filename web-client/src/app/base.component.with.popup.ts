export class BaseComponentWithPopup {
  public showPopup: boolean = false; // показывать всплывающие окно
  public message: string; // сообщение всплывающего окна
  public isError: boolean = false; // окно об ошибке или информационные
  public isSuccess: boolean = false; // окно об успехе или информационные
  public isInfo: boolean = false; // окно информационное или информационные
  public display: string = 'none';// как окно будет отображаться в браузере

  constructor() {
  }

  initPopup(showPopup: boolean, typePopup: string, display: string): void {
    this.showPopup = showPopup;
    this.display = display;
    switch (typePopup) {
      case 'info':
        this.isInfo = true;
        break;
      case 'error':
        this.isError = true;
        break;
      case 'success':
        this.isSuccess = true;
        break;
      default:
        break;
    }
  }

  closePopup(): void {
    this.showPopup = false;
    this.display = 'none';
    this.isInfo = false;
    this.isSuccess = false;
    this.isError = false;
  }
}
