package domain.service;

import java.math.BigDecimal;
import java.util.List;

import javax.sound.midi.Instrument;

public interface RegulatoryService {

	BigDecimal valuate(List<Instrument> instruments);

}