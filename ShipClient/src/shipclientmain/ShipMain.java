package shipclientmain;

import java.io.FileReader;
import java.io.LineNumberReader;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class ShipMain
{
   public static void main(String[] args)
   {
      org.omg.CORBA.ORB orb = null;
      try {
          orb = org.omg.CORBA.ORB.init(args, null);
      }
      catch (org.omg.CORBA.SystemException se) 
      { 
         System.err.println("ORB init failure " + se); 
         System.exit(1); 
      }
 

      org.omg.CORBA.Object initRef = null;
 

// Read IOR from file

      try 
      {
         LineNumberReader input = new LineNumberReader(new FileReader("ns.ior"));
         initRef = orb.string_to_object(input.readLine());
      }
      catch(java.io.IOException e) 
      {
         System.out.println("Exception: " + e);
         System.exit(1);
      }

// End IOR

      NamingContext initContext = null;
      try {
         initContext = NamingContextHelper.narrow(initRef);
      }
      catch (org.omg.CORBA.SystemException se) 
      { 
         System.err.println("Context narrow failure " + se); 
         System.exit(1); 
      }
 

      NameComponent[] name = new NameComponent[3];
      name[0] = new NameComponent("objects", "");
      name[1] = new NameComponent("military", "");
      name[2] = new NameComponent("navy", "");

      org.omg.CORBA.Object objRef = null;
      try {
         objRef = initContext.resolve(name);
      }
      catch (org.omg.CORBA.SystemException se) 
      { 
         System.err.println("Resolve name failure " + se); 
         System.exit(1); 
      }
      catch (org.omg.CORBA.UserException ue) 
      { 
         System.err.println("Resolve name failure " + ue); 
         System.exit(1); 
      }

      Ship.AircraftCarrier carrier = null;
      try {
      carrier = Ship.AircraftCarrierHelper.narrow(objRef);
      }
      catch (org.omg.CORBA.SystemException se) 
      { 
         System.err.println("AircraftCarrier narrow failure " + se); 
         System.exit(1); 
      }

      // Standard program continues 

      String flight = args.length > 0 ? args[0]: "Ghost Rider 101";

      Ship.Aircraft aircraft = null;
      try {
      aircraft = carrier.launch(flight);
      }
      catch (org.omg.CORBA.SystemException se) 
      { 
         System.err.println("Carrier launch failure " + se); 
         System.exit(1); 
      }

      String designation = null;
      try {
         designation = aircraft.codeNumber();
         System.out.println("Aircraft " + designation + " is airborne");
      }
      catch (org.omg.CORBA.SystemException se) 
      { 
         System.err.println("Get name failure " + se); 
         System.exit(1); 
      }

      try {
         int fuel = aircraft.fuelCapacity();
         System.out.println ("Aircraft " + designation + " has " + fuel + " Pounds");
      }
      catch (org.omg.CORBA.SystemException se) 
      { 
         System.err.println("Get fuel failure " + se); 
         System.exit(1); 
      }
   }
}
