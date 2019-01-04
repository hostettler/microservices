import csv
import random
import datetime
import time
from datetime import date


def random_date(start, end):
    """Generate a random datetime between `start` and `end`"""
    return start + datetime.timedelta(
        # Get a random amount of seconds between `start` and `end`
        seconds=random.randint(0, int((end - start).total_seconds())),
    )

with open('Euronext_Equities_EU.csv', newline='') as equityCsvfile:
	reader = csv.DictReader(equityCsvfile)
	equityData = list(csv.reader(equityCsvfile))
#print(equityData)


with open('Euronext_Bonds_EU.csv', newline='') as bondCsvfile:
	reader = csv.DictReader(bondCsvfile)
	bondData = list(csv.reader(bondCsvfile))
#print(bondData)


with open('Euronext_funds_EU.csv', newline='') as fundCsvfile:
	reader = csv.DictReader(fundCsvfile)
	fundData = list(csv.reader(fundCsvfile))
#print(fundData)

with open('Euronext_StructureProduct_EU.csv', newline='') as spCsvfile:
	reader = csv.DictReader(spCsvfile)
	spData = list(csv.reader(spCsvfile))
#print(spData)

with open('lei.csv', newline='') as leiCsvfile:
	reader = csv.DictReader(leiCsvfile)
	leiData = list(csv.reader(leiCsvfile))
#print(leiData)


currency = ['USD', 'CHF', 'EUR', 'SGD', 'GBP']
insert = 'INSERT INTO Instrument (ID, instrumentType, brokerLei, counterpartyLei, dealDate, originalCurrency, valueDate, isin, tracker, quantity, maturityDate, amountInOriginalCurrency, strikeAmount, direction) values ( INSTRUMENT_SEQ.nextval, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}  );'

class Instrument:
	def __init__(self, data, discriminant, isinPos, valuePos, maturityPos, trackerPos, strikePos, dirPos, currencyPos):
		self.data = data
		self.discriminant = discriminant
		self.isinPos = isinPos
		self.valuePos = valuePos
		self.maturityPos = maturityPos
		self.trackerPos = trackerPos
		self.strikePos = strikePos
		self.dirPos = dirPos
		self.currencyPos = currencyPos


bond = Instrument(bondData, 'B', 1, 9, 4, None, None, None,5)
equity = Instrument(equityData, 'S', 0, 3, None,1, None, None, 2)
fund = Instrument(fundData, 'S', 0, 3, None,1, None, None, 4)
sp = Instrument(spData, 'W', 1, 10, None,2, 5, 4, 7)

def generateComplex(instrument, numberOfInstrumentToGenerate):
	for i in range(0, numberOfInstrumentToGenerate):
		discriminantVal = "'" + instrument.discriminant +  "'"
		brokerLei="'" + random.choice(leiData)[0] + "'"
		counterpartyLei="'" + random.choice(leiData)[0] + "'"

		while True:
			valid=True
			instr = random.choice(instrument.data)
			amount = instr[instrument.valuePos]
			if amount != '-' and amount != 'Last' and amount != '':
				try:
					float(amount)
				except ValueError:
					valid=False
			else:
				valid=False
			if valid and instrument.maturityPos is not None:			
				maturity = instr[instrument.maturityPos]
				if maturity != '-' and maturity != 'Last' and maturity != '':
					try:
						datetime.datetime.strptime(maturity, '%m/%d/%Y')
					except ValueError:
						valid=False
				else:
					valid=False
			if valid == True :
				break
		amount = instr[instrument.valuePos]
		originalCurrency="'" + instr[instrument.currencyPos] + "'"
		isin="'" + instr[instrument.isinPos] + "'"
		if instrument.maturityPos is not None:
			maturity = "PARSEDATETIME('" + instr[instrument.maturityPos] + "','mm/dd/yyyy','en')"
		else:
			maturity = 'NULL'
		if instrument.trackerPos is not None:
			tracker = "'" + instr[instrument.trackerPos] + "'"
		else:
			tracker = 'NULL'
		if instrument.strikePos is not None:
			strike = instr[instrument.strikePos] 
		else:
			strike = 'NULL'
		if instrument.dirPos is not None:
			dir = "'" + instr[instrument.dirPos] + "'"
		else:
			dir = 'NULL'
		valueDate = "PARSEDATETIME('"  + random_date(datetime.date(2008, 6, 24), date.today()).strftime('%d-%m-%Y')  + "','yyyy-dd-mm','en')"
		quantity = random.randint(1,10000)
		amount = float(amount) * quantity
		print (insert.format(discriminantVal, brokerLei, counterpartyLei, valueDate, originalCurrency, valueDate, isin, tracker, quantity,  maturity, amount, strike, dir))

def generateSimple(discriminant, numberOfInstrumentToGenerate):
	for i in range(0, numberOfInstrumentToGenerate):
		discriminantVal = "'" + discriminant +  "'"
		brokerLei="'" + random.choice(leiData)[0] + "'"
		counterpartyLei="'" + random.choice(leiData)[0] + "'"
		originalCurrency="'" + random.choice(currency) + "'"
		maturity = "PARSEDATETIME('"  + random_date(date.today(), datetime.date(2099, 6, 24)).strftime('%d-%m-%Y') + "','yyyy-dd-mm','en')"
		valueDate = "PARSEDATETIME('"  + random_date(datetime.date(2008, 6, 24), date.today()).strftime('%d-%m-%Y') + "','yyyy-dd-mm','en')"
		quantity = 1
		isin='NULL'
		tracker='NULL'
		strike='NULL'
		dir='NULL'
		amount = random.randint(100000,1000000)
		print (insert.format(discriminantVal, brokerLei, counterpartyLei, valueDate, originalCurrency, valueDate, isin, tracker, quantity,  maturity, amount, strike, dir))

min = 100
max = 1000
generateComplex(bond, random.randint(min,max))
generateComplex(equity, random.randint(min,max))
generateComplex(sp, random.randint(min,max))
generateSimple('L', random.randint(min,max))
generateSimple('D', random.randint(min,max))
