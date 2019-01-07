package api.msg;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InstrumentConsumerTest {

	@Mock
	private InstrumentProducer producer;
	
	@InjectMocks
	private InstrumentConsumer consumer;
	
	@Test
	void testUpdateRegularInstrument() {
		consumer.updateInstrument("452");
		verify(producer).send(452l);
	}
	
	@Test
	void testUpdateAllInstrument() {
		consumer.updateInstrument("all");
		verify(producer, times(1)).sendAllInstruments();
	}
	
	@Test
	void testUpdateUnexpectedMessage() {
		assertThrows(IllegalArgumentException.class,
				() -> consumer.updateInstrument("XXX"));
	}

}
