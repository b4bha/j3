class MyDate
{
	int day,month,year;
	MyDate()
	{
		System.out.println("\nNo Date Initialised...");
	}
	MyDate(int d,int m,int y)
	{
		day=d;month=m;year=y;
	}
	public void display()		
	{
		System.out.println("Date is : "+day+"/"+month+"/"+year);
	}
	boolean checkdate()
	{
		boolean state;
        if(month<1 || month >12)
			return false;
			
		if(day<1 || day>31 )
			state=false;
		else
		{
			switch (month)
			{
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:if(day > 31)
						{
							state= false;
						}
						else
							state= true;
						break;
				case 4:
				case 6:
				case 9:
				case 11:if(day > 30)
						{
							state= false;
						}
						else
							state= true;
						break;
				case 2:if(year % 4 != 0 && day > 28)
						{
							state= false;
						}
						else
							return true;
						if(day > 29)
						{
							state= false;
						}
						default:state= false;
			}
		}
		return state;
	}
}

class InvalidDateException extends Exception
{
	public InvalidDateException(String s)
	{
		super(s);
	}
}
	
class Slip3_1
{
	public static void main(String args[])
	{
		int d1,m1,y1;
		if(args.length != 3)
		{
			System.out.println("\nUse command line argument as:dd mm yyyy\n");
		}
		else
		{
			d1=Integer.parseInt(args[0]);
			y1=Integer.parseInt(args[2]);
			m1=Integer.parseInt(args[1]);
			MyDate a =new MyDate(d1,m1,y1);
			try
			{
					a.display();
					if(! a.checkdate())
					throw new InvalidDateException("Date is  Invalid");
					System.out.println("\nIs a Valid Date;");
			}
			catch(InvalidDateException e)
			{
				System.out.println(e);
			}
		}
	}
}