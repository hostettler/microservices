export class InstrumentStatistics {

    public currencyStatistics = {
        amounts : [],
        currencies : []
    }
    
     public instrumentStatistics = {
            amounts	 : [],
            types : []
     }

    constructor(statistics) {
        console.log(statistics);
        var instrumentBreakdown = statistics.breakdownByInstrumentType
        for(var property in statistics.breakdownByInstrumentType) {
            this.instrumentStatistics.types.push(property);
            var data = instrumentBreakdown[property];
            this.instrumentStatistics.amounts.push(data);            
        }
        var currencyBreakdown = statistics.breakdownByCurrency
        for(var property in currencyBreakdown) {
            this.currencyStatistics.currencies.push(property);
            var data = currencyBreakdown[property];
            this.currencyStatistics.amounts.push(data);            
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

 