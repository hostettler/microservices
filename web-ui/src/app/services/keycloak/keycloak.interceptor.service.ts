import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { KeycloakService } from './keycloak.service';
@Injectable()
export class KeycloakInterceptorService implements HttpInterceptor {
  constructor(
    private keycloakService: KeycloakService,
  ) {}
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.keycloakService.isLoggedIn()) {
      return this.getUserToken().pipe(
        mergeMap((token) => {
          if (token) {
            request = request.clone({
              setHeaders: {
                Authorization: `Bearer ${token}`,
              },
            });
          }
          return next.handle(request);
        }));
    }
    return next.handle(request);
  }
  getUserToken() {
    const tokenPromise: Promise<string> = this.keycloakService.getToken();
    const tokenObservable: Observable<string> = from(tokenPromise);
    return tokenObservable;
  }
}
