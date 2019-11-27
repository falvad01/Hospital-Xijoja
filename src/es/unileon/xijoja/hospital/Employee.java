package es.unileon.xijoja.hospital;

import java.sql.Date;

public class Employee {

	private int id;
	private String name;
	private String surname1;
	private String surname2;
	private String NIE;
	private Date date;
	private String bankAccount;
	private String puesto;
	private String password;
	private String user;
	private String email;

	public Employee(int id, String name, String surname1, String surname2, String NIE, Date date, String bankAccount,
			String puesto, String password, String user, String email) {

		this.id = id;
		this.name = name;
		this.surname1 = surname1;
		this.surname2 = surname2;
		this.NIE = NIE;
		this.date = date;
		this.bankAccount = bankAccount;
		this.puesto = puesto;
		this.password = password;
		this.user = user;
		this.email = email;

	}

}
