export class InstrumentStatistics {

    public currencyStatistics = {
        amounts : [],
        currencies : [],
    };

     public instrumentStatistics = {
            amounts	 : [],
            types : [],
     };

    constructor(statistics: any) {
        console.info(statistics);
        const instrumentBreakdown = statistics.breakdownByInstrumentType;
        for (const property in statistics.breakdownByInstrumentType) {
            if (instrumentBreakdown[property]) {
                this.instrumentStatistics.types.push(property);
                const data = instrumentBreakdown[property];
                this.instrumentStatistics.amounts.push(data);
            }
        }
        const currencyBreakdown = statistics.breakdownByCurrency;
        for (const property in currencyBreakdown) {
            if (currencyBreakdown[property]) {
                this.currencyStatistics.currencies.push(property);
                const data = currencyBreakdown[property];
                this.currencyStatistics.amounts.push(data);
            }
        }
    }

    public getCurrencies () {
        return this.currencyStatistics.currencies;
    }

    public getCurrenciesAmounts () {
        return this.currencyStatistics.amounts;
    }


    public getInstrumentTypes () {
        return this.instrumentStatistics.types;
    }

    public getInstrumentAmounts () {
        return this.instrumentStatistics.amounts;
    }

}
