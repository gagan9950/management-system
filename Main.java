import java.util.*;

class Address 
{
	protected String name;
	protected Integer streetNumber;
	protected String streetName;
	protected String city;
	protected String state;
	protected Integer zipcode;

	public Address(String name,Integer streetNumber,String streetName,String city,String state,Integer zipcode) 
	{
		this.name=name;
		this.streetNumber=streetNumber;
		this.streetName=streetName;
		this.city=city;
		this.state=state;
		this.zipcode=zipcode;
	}

	public Address getAddress() 
	{
		return this;
	}
}

class Book 
{
	protected Integer id;
	protected String title;
	protected String category;
	protected String author;
	protected Date dueDate;

	public Book(Integer id, String title, String category, String author) 
	{
		this.id=id;
		this.title=title;
		this.category=category;
		this.author=author;
	}

	public void setDueDate(Date date) 
	{
		this.dueDate = date;
	}

}

class User 
{
	protected Integer id;
	protected Address address;
	protected double lateFee;
	protected List<Book> booksBorrowed;

	public User(Integer id,Address address) 
	{
		this.id=id;
		this.address=address;
		booksBorrowed=new ArrayList<>();
	}

	public void getBooksBorrowed() 
	{
		for(Book book: booksBorrowed) 
		{
			System.out.println(" Id - "+book.id+", title - "+book.title);
		}
	}

	public boolean borrowBook(Book book) 
	{
		if(lateFee>0.00) 
		{
			System.out.println("Please return/renew old books and pay late fee, before borrowing new book!");
			return false;
		}
		book.setDueDate(new Date());
		this.booksBorrowed.add(book);
		return true;
	}

	public boolean returnBook(Book book) 
	{
		if(booksBorrowed.indexOf(book)>=0) 
		{
			Integer overDueBy=overDueByDays(book);
			if(overDueBy>10) 
			{
				lateFee=lateFee+((overDueBy-10)*1.00);
			}
			this.booksBorrowed.remove(book);
			return true;
		}
		return false;
	}

	private Integer overDueByDays(Book book) 
	{
		Date today=new Date();
		Long diffInMS=Math.abs(today.getTime()-book.dueDate.getTime());
		Long diffInDays=diffInMS/(24*60*60*1000);
		return diffInDays.intValue();
	}

}

class Library 
{
	protected Integer libraryId;
	protected Address address;
	protected Map<Integer,Book> books=new HashMap<>();
	protected Map<Integer,User> users=new HashMap<>();

	public Library(Integer libraryId,Address address) 
	{
		this.address=address;
		this.libraryId=libraryId;
	}

	public void setAddress(Address address) 
	{
		this.address=address;
	}

	public boolean addBook(Book book) 
	{
		if(!books.containsValue(book)) 
		{
			books.put(book.id,book);
			return true;
		}
		return false;
	}

	public boolean deleteBook(Book book) 
	{
		if(!books.containsValue(book)) 
		{
			books.remove(book.id);
			return true;
		}
		return false;
	}

	public boolean addUser(User user) 
	{
		if(!users.containsValue(user)) 
		{
			System.out.println("Added user - "+user.id);
			users.put(user.id,user);
			return true;
		}
		System.out.println("Unable to add user - "+user.id);
		return false;
	}

	public boolean deleteUser(User user) 
	{
		if(!users.containsValue(user)) 
		{
			users.remove(user.id);
			return true;
		}
		return false;
	}

	public User findUser(Integer userId) 
	{
		return users.get(userId);
	}

	public Book findBook(Integer bookId) 
	{
		return books.get(bookId);
	}
}
public class Main 
{
	public static void main(String args[])
	{
		
	}
}