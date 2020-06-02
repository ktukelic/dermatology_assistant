import {Injectable} from '@angular/core';
import {PORT} from '../shared/const';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class MonitoringService {

  private server = `http://localhost:${PORT}/api/patient/monitoring`;

  private stompClient = null;

  Toast = Swal.mixin({
    toast: true,
    position: 'bottom-end',
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    // onOpen: (toast) => {
    //   toast.addEventListener('mouseenter', Swal.stopTimer)
    //   toast.addEventListener('mouseleave', Swal.resumeTimer)
    // }
  });

  constructor() {
    const socket = new SockJS(this.server);
    this.stompClient = Stomp.over(socket);
  }

  connect() {
    const socket = new SockJS(this.server);
    this.stompClient = Stomp.over(socket);

    const that = this;
    // tslint:disable-next-line:only-arrow-functions
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe('/monitoring', (notif) => {
        if (notif.body) {
          that.Toast.fire({
            icon: 'warning',
            title: notif.body
          });
        }
      });
    });
  }

  disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
  }
}
