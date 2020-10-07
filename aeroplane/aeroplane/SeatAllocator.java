package aeroplane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeatAllocator {

	private Map<Seat, Passenger> allocation;

	private static final String CREW = "crew";
	private static final String BUSINESS = "business";
	private static final String ECONOMY = "economy";
	
	public SeatAllocator() {
		allocation = new HashMap<Seat, Passenger>();
	}

	@Override
	public String toString() {
		return allocation.toString();
	}
	
	private void allocateInRange(Passenger passenger, Seat first, Seat last) throws AeroplaneFullException {
		Seat seat = first;
		while (true) {
			if (allocation.get(seat) == null && (!seat.isEmergencyExit() || passenger.isAdult())) {
				allocation.put(seat, passenger);
				break;
			}
			if (seat == last || !seat.hasNext()) {
				throw new AeroplaneFullException();
			}
			seat = seat.next();
		}
	}

	private static String readStringValue(BufferedReader br) throws MalformedDataException, IOException {

		String result = br.readLine();
		
		if(result == null) {
			throw new MalformedDataException();
		}
		
		return result;
		
	}

	private static int readIntValue(BufferedReader br)
			throws MalformedDataException, IOException {
		try {
			return Integer.parseInt(readStringValue(br));
		} catch(NumberFormatException e) {
			throw new MalformedDataException();
		}
	}

	private static Luxury readLuxuryValue(BufferedReader br)
			throws MalformedDataException, IOException {
		try {
			return Luxury.valueOf(readStringValue(br));
		} catch(IllegalArgumentException e) {
			throw new MalformedDataException();
		}
	}

	
	public void allocate(String filename) throws IOException, AeroplaneFullException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));

		String line;
		while((line = br.readLine()) != null) {
			try {
				if(line.equals(CREW)) {
					allocateCrew(br);
				} else if(line.equals(BUSINESS)) {
					allocateBusiness(br);
				} else if(line.equals(ECONOMY)) {
					allocateEconomy(br);
				} else {
					throw new MalformedDataException();
				}
			} catch(MalformedDataException e) {
				System.out.println("Skipping malformed line of input");
			}
		}
		
	}
	
	private void allocateCrew(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		Passenger crewMember = new CrewMember(firstName, lastName);
		allocateInRange(crewMember, Seat.CREW_START_SEAT, Seat.CREW_END_SEAT);
	}

	private void allocateBusiness(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		int age = readIntValue(br);
		Luxury luxury = readLuxuryValue(br);
		Passenger businessPassenger = new BusinessPassenger(firstName, lastName, age, luxury);
		allocateInRange(businessPassenger, Seat.BUSINESS_START_SEAT, Seat.BUSINESS_END_SEAT);
	}

	private void allocateEconomy(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
		String firstName = readStringValue(br);
		String lastName = readStringValue(br);
		int age = readIntValue(br);
		Passenger economyPassenger = new EconomyPassenger(firstName, lastName, age);
		allocateInRange(economyPassenger, Seat.ECONOMY_START_SEAT, Seat.ECONOMY_END_SEAT);
	}

	public void upgrade() throws AeroplaneFullException {
		Seat economySeat = Seat.ECONOMY_START_SEAT;
		while (economySeat.hasNext()) {
			if (!allocation.containsKey(Seat.BUSINESS_END_SEAT)) {
				allocateInRange(allocation.get(economySeat), Seat.BUSINESS_START_SEAT, Seat.BUSINESS_END_SEAT);
				allocation.remove(economySeat);
			} else {
				break;
			}
			economySeat = economySeat.next();
		}
	}

}
