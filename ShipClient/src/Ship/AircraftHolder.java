package Ship;

/**
* Ship/AircraftHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Ship.idl
* 01 May�s 2020 Cuma 02:05:32 EET
*/

public final class AircraftHolder implements org.omg.CORBA.portable.Streamable
{
  public Ship.Aircraft value = null;

  public AircraftHolder ()
  {
  }

  public AircraftHolder (Ship.Aircraft initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Ship.AircraftHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Ship.AircraftHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Ship.AircraftHelper.type ();
  }

}
