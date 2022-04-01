import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root',
})

export class InstrumentService {

    // Define API
    constructor(private http: HttpClient) { }

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
        }),
    };

    // HttpClient API get() method => Fetch employees list
    getCount(): Observable<Number> {
        return this.http.get<Number>(environment.instrumentService.url + '/count')
            .pipe(
                retry(1),
                catchError(this.handleError),
            );
    }

    // HttpClient API get() method => Fetch employees list
    updateValuation(): void {
        this.http.post<any>(environment.instrumentService.url
            + "/propagateAllInstruments", "",
            this.httpOptions,
        ).toPromise().then((data: any[]) => {
        }).catch((error) => {
            console.error(`Promise rejected with ${JSON.stringify(error)}`);
        });
    }

    // Error handling
    handleError(error) {
        let errorMessage = '';
        if (error.error instanceof ErrorEvent) {
            // Get client-side error
            errorMessage = error.error.message;
        } else {
            // Get server-side error
            errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
        }
        console.error(errorMessage);
        return throwError(errorMessage);
    }
}
