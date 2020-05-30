package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdditionalDocImp;
import dao.AdminImpl;
import dao.ClientImpl;
import dao.ClientmailImpl;
import dao.DossierImpl;
import dao.EndedImpl;
import dao.GraphinfoImp;
import dao.IAdmin;
import dao.IClient;
import dao.IClientmail;
import dao.IDossier;
import dao.IEnded;
import dao.IMessage;
import dao.IResponsable;
import dao.IST;
import dao.ISaved;
import dao.ITache;
import dao.IadditionalDoc;
import dao.Igraphinfo;
import dao.MessageImpl;
import dao.RespoImpl;
import dao.STIMPL;
import dao.SavedImpl;
import dao.TacheImpl;
import entities.AdditionalDoc;
import entities.Admin;
import entities.Client;
import entities.Clientmail;
import entities.Dossier;
import entities.Messenger;
import entities.Ended;
import entities.Graphinfo;
import entities.Responsable;
import entities.Saved;
import entities.SousTache;
import entities.Tache;




@WebServlet({"/Acceuil","/Admin","/Respo","/tacheApprouver","/tacheEnCours","/tacheFinis",
	"/ajoutDoc","/ajoutRespo","/ajoutTache","/login","/deconnexion",
	"/progression","/deleteDoc","/deleteRespo","/editRespo","/editDoc" , 
	"/next","/approuve","/addST","/viewST","/rollback","/tacheVenir","/contact",
	"/viewTache","/deleteTache","/viewSTAdmin","/addSTAdmin","/editTache","/deleteSTdbsh",
	"/messenger","/deleteSTAdmin","/listMessenger","/deleteMsgDbl3ani","/loginClient", "/prog",
	"/progHeader","/registration","/premium","/editClient","/deleteClient","/acceuilClient",
	"/dossierClient","/profile","/editProfile","/endPremium","/saved","/deleteSaved","/addSaved",
	"/details","/CU","/Propos","/loginAdmin","/loginRespo","/statistics","/deconnexionRespo","/confirmrequest"
	,"/askadditional","/sendemail","/sendrequest","/sendrecovery","/resetPassword","/setNewPassword",
	"/forgotPassword","/index.html"})

public class TrackingServlet extends HttpServlet { 
	
		
	
	private ITache tacheDAO;
	private IResponsable respoDAO;
	private IDossier dossierDAO;
	private IEnded endedDAO;
	private IST STDAO;
	private IClientmail clientmailDAO;
	private IClient clientDAO;
	private IMessage messageDAO;
	private ISaved savedDAO;
	private Igraphinfo graphinfoDAO;
	private IAdmin adminDao;
	private IadditionalDoc additionalDocDAO;
	HttpSession session;
	
	@Override
	public void init() throws ServletException {
		tacheDAO = new TacheImpl();
		respoDAO = new RespoImpl();
		dossierDAO = new DossierImpl();
		endedDAO = new EndedImpl();
		STDAO = new STIMPL();
		clientmailDAO = new ClientmailImpl();
		clientDAO = new ClientImpl();
		messageDAO = new MessageImpl();
		savedDAO = new SavedImpl();
		graphinfoDAO=new GraphinfoImp();
		adminDao= new AdminImpl();
		additionalDocDAO=new AdditionalDocImp();
	}

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try{
			session=request.getSession();
			if(request.getServletPath().equals("/index.html")) {
				request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
			}
			if(request.getServletPath().equals("/resetPassword")){
				System.out.println("hahia");
				String id=request.getParameter("key");
				System.out.println("id1="+id);
				session.setAttribute("key", id);
				Client cl =clientDAO.getClient(id);
	           cl.setEmail(clientmailDAO.getMailClient(cl.getId_client()));
	System.out.println(cl.toString());
	System.out.println(cl.getEmail());
				 if(clientDAO.login(cl.getEmail(), cl.getPassword())) {
					 System.out.println("here we go");
					 request.setAttribute("email1",cl.getEmail());
						request.getRequestDispatcher("WEB-INF/setPassword.jsp").forward(request, response);
				 }
			}
			if(request.getServletPath().equals("/forgotPassword")) {
				request.getRequestDispatcher("WEB-INF/forgotpassword.jsp").forward(request, response);
			}
			if(request.getServletPath().equals("/setNewPassword")) {
				System.out.println("daa");
				String id=(String) session.getAttribute("key");
				System.out.println("id2="+id);
				String pass1=request.getParameter("pass1");
				String pass2=request.getParameter("pass2");
				System.out.println("pass1:"+pass1);
				System.out.println("pass2:"+pass2);
				if(pass1.equals(pass2)) {
					System.out.println("the same");
					Client cl = clientDAO.getClient(id);
					System.out.println(cl.getEmail());
					System.out.println("debug2");
					System.out.println("debug3");
					clientDAO.editClient(cl.getId_client(), cl.getNom(), pass1, cl.getAdresse(),cl.getTel());
					System.out.println("edited");
					request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
				}
			}
			
			
			
			/*
			if(request.getServletPath().equals("/loginAdmin")) {
				request.getRequestDispatcher("WEB-INF/loginAdmin.jsp").forward(request, response);
			}
			*/
			if(request.getServletPath().equals("/askadditional")) {
				System.out.println("askinitial");

				String id_doc = request.getParameter( "id_doc" );
				String respo =tacheDAO.RespoAdmin(Integer.parseInt(id_doc));
				String idclient=request.getParameter("nomClient");
				request.setAttribute("nomRespo", respo);
				request.setAttribute("id_doc", id_doc);
				request.setAttribute("nomClient", idclient);
				System.out.println("askfinal");
				request.getRequestDispatcher("WEB-INF/askadditional.jsp").forward(request, response);
			}
			if(request.getServletPath().equals("/confirmrequest")) {
				
				
				System.out.println("confirm");
				session=request.getSession();
				System.out.println("confirm1");
				Admin a=new Admin();
				System.out.println("confirm2");
				a=(Admin) session.getAttribute("admin");
				System.out.println("confirm3");
				if(a==null)	{
					request.getRequestDispatcher("WEB-INF/loginAdmin.jsp").forward(request, response);	
				}
				int id_add=Integer.parseInt(request.getParameter("id_add"));
				AdditionalDoc add=additionalDocDAO.getadditionalDoc(id_add);
				String emailClient=dossierDAO.getEmail(add.getId_tache());
				final String body="<p><span style=\"font-family: \r\n" + 
						"        'Lucida Console', Monaco, monospace;\">hello Dear ,</span></p>\r\n" + 
						"<p><span style=\"font-family:" + 
						"        'Lucida Console', Monaco, monospace;\">Tracking "
						+ "Der Service hat Ihnen diese E-Mail gesendet, um Sie dar&uuml;ber zu informieren, dass wir  &nbsp;&quot;"+add.getType()+"&quot;benv&ouml;tigen,</span></p> "
						+
						" <p><span style=\"font-family:" + 
						"        'Lucida Console', Monaco, monospace;\"></span></p>" + 
						"<p><span style=\"font-family: " + 
						"        'Lucida Console', Monaco, monospace;\">um die Behandlung Ihres Dokuments abzuschlie&szlig;en N :"+add.getId_tache()+"</span></p>"
						+ 
								"<p><br></p>"+
						"<blockquote>" + 
						"    <p><strong><span style=\"font-size: 18px; background-color: rgb(184, 49, 47); color: rgb(239, 239, 239);\">Die Frist f&uuml;r den Versand des Dokuments ist :	"+add.getDate()+" <br></span></strong></p>" + 
						"</blockquote>" + 
						"<div id=\"gtx-trans\" style=\"position: absolute; left: -44px; top: -2px;\">" + 
						"    <div class=\"gtx-trans-icon\"><span style=\"font-size: " + 
						"      18px;\"><strong><br></strong></span></div>" + 
						"</div> "+
						
						"<p><strong><span style=\"color: rgb(41, 105, 176);\"><br></span></strong></p>\r\n" + 
						"<p><strong><span style=\"color: rgb(41, 105, 176);\">Thank you</span></strong></p>"+
						"<p><strong><span style=\"color: rgb(44, 130, 201);\"><br></span></strong></p>\r\n" + 
						"<p><strong><span style=\"color: rgb(44, 130, 201);\">Tracking Service Team .</span></strong></p>";
				
				
				System.out.println("email akhay"+emailClient);
				request.setAttribute("clientmail",emailClient);
				request.setAttribute("bodyemail",body);
				request.setAttribute("subject","eine Aufforderung, zus&auml;tzliche Dokumente zu senden, um die Behandlung abzuschließen");
				System.out.println("confirmfinal");
				request.getRequestDispatcher("WEB-INF/confirmrequest.jsp").forward(request, response);
				
			}
			if(request.getServletPath().equals("/deconnexionRespo")) {
				Responsable r= (Responsable) request.getSession().getAttribute("session");
				respoDAO.online(false,r.getId_respo());
				session = request.getSession();
				session.invalidate();
				request.setAttribute("succes", "Sie haben sich ausgeloggt ");
				request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
			}
			if(request.getServletPath().equals("/statistics")) {
				session=request.getSession();
				Admin a=new Admin();
				a=(Admin)session.getAttribute("admin");
				if(a==null)	{
					request.getRequestDispatcher("WEB-INF/loginAdmin.jsp").forward(request, response);	
				}
				List<Graphinfo> infos= graphinfoDAO.infos(4);
				List<AdditionalDoc> additional=additionalDocDAO.additionalDoc();
				Graphinfo.count=0;
				int total=0;
				int nbrDossier=dossierDAO.ListDossier().size();
				int nbrResponsable=respoDAO.ListRespo().size();
				int nbrResponsableTache=respoDAO.countRepoTache();
				int registredClient=clientDAO.ListClient().size();
				int totalClient = dossierDAO.getAllclient();
				int premiumAccount=clientDAO.getPremiumCount();
				
				int RespoHasTacheCount =respoDAO.countRepoTache();
				System.out.println("habe einen aktuellen tache=");
				System.out.println("total client :"+totalClient);
				request.setAttribute("admin",a);
				for(Graphinfo d :infos) {
					total+=d.getOperations();
				}
				System.out.println("total: "+total);
				for(Graphinfo d :infos) {
					d.setHeight(d.getOperations()*300/10);
				}
				
				
				/*tab filling
				 * end graphs
				 */
				List<Responsable>respo=respoDAO.ListRespo();
				
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String email = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(email);
					//System.out.println(client.toString());
				}
				request.setAttribute("client", clyan);		
				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respons = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respons);
					
				}
				request.setAttribute("graphData", infos);
				request.setAttribute("RespoHasTacheCount", RespoHasTacheCount);
			    request.setAttribute("premiumAccounts", premiumAccount);
				request.setAttribute("total", total);
				request.setAttribute("nbrDossier",nbrDossier);
				request.setAttribute("nbrResponsable",nbrResponsable);
				request.setAttribute("nbrResponsableTache",nbrResponsableTache);
				request.setAttribute("nbrClient",totalClient);
				request.setAttribute("registredClient",registredClient);
				request.setAttribute("dossier", dossiers);
				request.setAttribute("responsables", respo);
				request.setAttribute("additional", additional);
				/*
				for(AdditionalDoc a :additional) {
					System.out.println(a.toString());
				}
				*/
				request.getRequestDispatcher("WEB-INF/statistics.jsp").forward(request, response);	
			
			}
			
			if(request.getServletPath().equals("/loginAdmin")) {
				request.getRequestDispatcher("WEB-INF/loginAdmin.jsp").forward(request, response);
			}
			
			if(request.getServletPath().equals("/loginRespo")) {
				request.getRequestDispatcher("WEB-INF/loginRespo.jsp").forward(request, response);
			}
		
		if(request.getServletPath().equals("/Admin")) {
			System.out.println("confirm33");
			session=request.getSession();
			Admin a=new Admin();
			a=(Admin) session.getAttribute("admin");
			System.out.println("confirm3");
			if(a==null)	{
				request.getRequestDispatcher("WEB-INF/loginAdmin.jsp").forward(request, response);	
			}
			request.getRequestDispatcher("WEB-INF/statistics.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/registration")) {
			String id_doc = request.getParameter( "id_doc" );
			request.setAttribute("id_doc", id_doc);
			String email = clientmailDAO.getMail(Integer.parseInt(id_doc));
			request.setAttribute("email", email);
			Dossier d =dossierDAO.getDoc(id_doc);
			String nom = d.getNom_cl();
			request.setAttribute("nom", nom);
			request.getRequestDispatcher("WEB-INF/registration.jsp").forward(request, response);
		}
		

		
		if(request.getServletPath().equals("/addSaved")) {
			String id_doc = request.getParameter( "id_doc" );
			String id_client = request.getParameter( "id_client" );
			int id=Integer.parseInt(id_client);
			int idD=Integer.parseInt(id_doc);
			Dossier dd=dossierDAO.getDosser(idD);
			String nomClient = dd.getNom_cl();
			String typa=dd.getType();
			savedDAO.addSaved(id_doc,id_client,nomClient,typa);
			//charger la liste
			List<Saved> dossiers =  savedDAO.ListSaved(id);
			for (Saved dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				
			}
			request.setAttribute("succes", "Ordner gut gespeichert");
			request.setAttribute("dossier", dossiers);
			request.getRequestDispatcher("WEB-INF/saved.jsp").forward(request, response);
		}

		
		if(request.getServletPath().equals("/deleteSaved")) {
			String id_doc = request.getParameter( "id_doc" );
			String id_client = request.getParameter( "id_client" );
			int id=Integer.parseInt(id_client);
			savedDAO.deleteSaved(id_doc,id_client);
			//charger la liste
			List<Saved> dossiers =  savedDAO.ListSaved(id);
			for (Saved dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				
			}
			request.setAttribute("succes", "Ordner gut geloscht");
			request.setAttribute("dossier", dossiers);
			int totalSaved = savedDAO.countSaved(id);
			request.setAttribute("totalSaved", totalSaved);
			request.getRequestDispatcher("WEB-INF/saved.jsp").forward(request, response);
		}


		
		if(request.getServletPath().equals("/editProfile")) {
			Client clyan = (Client) request.getSession().getAttribute("session");
			request.setAttribute("client", clyan);
			request.getRequestDispatcher("WEB-INF/editProfile.jsp").forward(request, response);
		}


		
		if(request.getServletPath().equals("/CU")) {
			request.getRequestDispatcher("WEB-INF/CU.jsp").forward(request, response);
		}


		
		if(request.getServletPath().equals("Propos")) {
			request.getRequestDispatcher("WEB-INF/Propos.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/saved")) {
			Client clyan = (Client) request.getSession().getAttribute("session");
			int id = clyan.getId_client();
			List<Saved> dossiers =  savedDAO.ListSaved(id);
			for (Saved dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				
			}
			int totalSaved = savedDAO.countSaved(id);
			request.setAttribute("totalSaved", totalSaved);
			request.setAttribute("client", clyan);
			request.setAttribute("dossier", dossiers);
			request.getRequestDispatcher("WEB-INF/saved.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/Acceuil")) {
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
		

		if(request.getServletPath().equals("/endPremium")) {
			String id_client = request.getParameter( "id_client" );
			clientDAO.editPrem(id_client);
			Client clyan = clientDAO.getClient(id_client);
			String nom_cl = clyan.getNom();
			List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("client", clyan);
			request.setAttribute("dossier", dossiers);
			
			request.setAttribute("succes", "Sie sind kein PREMIUM-Kunde mehr lol");
			request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
		}

		
		if(request.getServletPath().equals("/profile")) {
			Client clyan = (Client) request.getSession().getAttribute("session");
			request.setAttribute("client", clyan);
			String email = clientmailDAO.getMailClient(clyan.getId_client());
			request.setAttribute("email", email);
			request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/acceuilClient")) {
			Client clyan = (Client) request.getSession().getAttribute("session");
			String nom_cl = clyan.getNom();
			List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("client", clyan);
			request.setAttribute("dossier", dossiers);
			request.getRequestDispatcher("WEB-INF/acceuilClient.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/progHeader")) {
			request.getRequestDispatcher("WEB-INF/progHeader.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/Acceuil")) {
			request.getRequestDispatcher("WEB-INF/Acceuil.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/loginClient")) {
			request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/deleteMsgDbl3ani")) {
			String id_mess = request.getParameter( "id_mess" );
			String msg = "Message Supprimé!";
			Messenger m = messageDAO.getMsg(id_mess);
			messageDAO.deleteDbl3ani(id_mess,msg);
			int idD = m.getId_doc();
			
			List<Messenger> mess =  messageDAO.ListMsg(idD);
			for (Messenger messenger : mess) {
				int idrr=messenger.getId_respo();
				Responsable respo = respoDAO.getRespo(String.valueOf(idrr));
				String nom_respo=respo.getNom();
				messenger.setNom_respo(nom_respo);
			}
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateur.getId_respo();
			List<Responsable> respo =  respoDAO.ListRespoMessage(idD);
			request.setAttribute("respo", respo);	
			request.setAttribute("messenger", mess);
			request.setAttribute("idD", idD);
			request.setAttribute("id", id_respooo);
			
			
			
			//chargement de liste Dossier
			int idR = utilisateur.getId_respo();
			Responsable respoo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respoo);
			//Header listes
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			
			request.getRequestDispatcher("WEB-INF/messenger.jsp").forward(request, response);
			
		}
		
		
		if(request.getServletPath().equals("/listMessenger")) {
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Dossier> doc =  dossierDAO.ListDossierRespo(id_respooo);
			request.setAttribute("dossier", doc);
			//chargement de liste Dossier
			
			Responsable respo = respoDAO.getRespo(String.valueOf(id_respooo));
			request.setAttribute("responsable", respo);
			//Header listes
			
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/listMessenger.jsp").forward(request, response);
		}
		
		
		
		if(request.getServletPath().equals("/messenger")) {
			String id_doc = request.getParameter( "id_doc" );
			int idD = Integer.parseInt(id_doc);
			List<Messenger> mess =  messageDAO.ListMsg(idD);
			for (Messenger messenger : mess) {
				int idrr=messenger.getId_respo();
				Responsable respo = respoDAO.getRespo(String.valueOf(idrr));
				String nom_respo=respo.getNom();
				messenger.setNom_respo(nom_respo);
			}
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateur.getId_respo();
			List<Responsable> respo =  respoDAO.ListRespoMessage(idD);
			request.setAttribute("respo", respo);	
			request.setAttribute("messenger", mess);
			request.setAttribute("idD", id_doc);
			request.setAttribute("id", id_respooo);
			
			
			//chargement de liste Dossier
			int idR = utilisateur.getId_respo();
			Responsable respoo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respoo);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			
			request.getRequestDispatcher("WEB-INF/messenger.jsp").forward(request, response);
		}
		
		
		

		if(request.getServletPath().equals("/viewSTAdmin")) {
			String id_tache = request.getParameter( "idST" );
			List<SousTache> tachee =  STDAO.ListST(Integer.parseInt(id_tache));
			int total = STDAO.NumST(Integer.parseInt(id_tache));
			request.setAttribute("total", total);
			request.setAttribute("tache", tachee);
			request.getRequestDispatcher("WEB-INF/viewSTAdmin.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/addSTAdmin")) {
			String id_tache = request.getParameter( "idST" );
			Tache tachtacha = tacheDAO.getOneOneTache(Integer.parseInt(id_tache));
			request.setAttribute("tache", tachtacha);
			request.getRequestDispatcher("WEB-INF/addSTAdmin.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/viewST")) {
			String id_tache = request.getParameter( "idST" );
			List<SousTache> tachee =  STDAO.ListST(Integer.parseInt(id_tache));
			request.setAttribute("tache", tachee);
			int total = STDAO.NumST(Integer.parseInt(id_tache));
			request.setAttribute("total", total);
			//chargement de liste Dossier
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int idR = utilisateur.getId_respo();
			Responsable respo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respo);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/viewST.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/viewTache")) {
			String id_doc = request.getParameter( "idST" );
			List<Tache> tachee =  tacheDAO.getTache(id_doc);
			request.setAttribute("tache", tachee);
			request.setAttribute("id", id_doc);

			request.getRequestDispatcher("WEB-INF/viewTaches.jsp").forward(request, response);
		}
		

		if(request.getServletPath().equals("/deleteTache")) {
			String idTache = request.getParameter( "id_d" );
			Tache tacha = tacheDAO.getOneOneTache(Integer.parseInt(idTache));
			tacheDAO.deleteTache(idTache);
			request.setAttribute("succes", "Der Fleck ist gut entfernt worden");
			int id = tacha.getId_doc();
			List<Tache> tachee =  tacheDAO.getTache(String.valueOf(id));
			request.setAttribute("tache", tachee);
			request.setAttribute("id",  id );

			request.getRequestDispatcher("WEB-INF/viewTaches.jsp").forward(request, response);
			
		} 
		
		
		if(request.getServletPath().equals("/addST")) {
			String id_tache = request.getParameter( "idST" );
			Tache tachtacha = tacheDAO.getOneOneTache(Integer.parseInt(id_tache));
			request.setAttribute("tache", tachtacha);
			//chargement de liste Dossier
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int idR = utilisateur.getId_respo();
			Responsable respo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respo);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/addST.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/ajoutDoc")) {
			String tracking = dossierDAO.getIdRandom(6);
			request.setAttribute("tracking", tracking);
			request.getRequestDispatcher("WEB-INF/addDoc.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/ajoutRespo")) {
			request.getRequestDispatcher("WEB-INF/addRespo.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/ajoutTache")) {
			String id_doc = request.getParameter( "id_doc" );
			List<Dossier> dossier =  dossierDAO.ListDossier();
			request.setAttribute("dossier", dossier);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			request.setAttribute("id_doc", id_doc);	
			request.getRequestDispatcher("WEB-INF/addTache.jsp").forward(request, response);
		}
		
		//-------------------------------------------
		
		if(request.getServletPath().equals("/Respo")) {
			Responsable utilisateurConnecte = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateurConnecte.getId_respo();
			session.setAttribute("session", utilisateurConnecte);
			String id= Integer.toString(id_respo);
			Responsable respo = respoDAO.getRespo(id);
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(id_respo);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/premium")) {
			String id_client = request.getParameter( "id_client" );
			
			request.setAttribute("id_client", id_client);
			request.getRequestDispatcher("WEB-INF/premium.jsp").forward(request, response);
		}
		

		
		
		if(request.getServletPath().equals("/tacheApprouver")) {
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateur.getId_respo();
			request.setAttribute("responsable", utilisateur);
			List<Tache> tachee =  tacheDAO.ListTacheRespoApprouver(id_respo);
			request.setAttribute("tache", tachee);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/approuver.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/tacheEnCours")) {
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateur.getId_respo();
			session.setAttribute("session", utilisateur);
			request.setAttribute("responsable", utilisateur);
			List<Tache> tach =  tacheDAO.ListTacheRespoEnCours(id_respo);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/enCours.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/tacheVenir")) {
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateur.getId_respo();
			session.setAttribute("session", utilisateur);
			request.setAttribute("responsable", utilisateur);
			List<Tache> tach =  tacheDAO.ListVenir(id_respo);
			request.setAttribute("tache", tach);
			
			
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			int totalVenir = tacheDAO.TotalVenir(id_respooo);
			request.setAttribute("totalVenir", totalVenir);
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/venir.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/tacheFinis")) {
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateur.getId_respo();
			session.setAttribute("session", utilisateur);
			request.setAttribute("responsable", utilisateur);
			List<Ended> tach =  endedDAO.ListTachat(id_respo);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			int totalEnded = endedDAO.countEnded(id_respo);
			request.setAttribute("totalEnded", totalEnded);
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/finis.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/deconnexion")) {
			session = request.getSession();
			session.invalidate();
			request.setAttribute("succes", "Sie haben sich ausgeloggt ");
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/login")) {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}

		
		if(request.getServletPath().equals("/deleteSTAdmin")) {
			String idST = request.getParameter("idST");
			STDAO.deleteSTdbsh(Integer.parseInt(idST));
			request.setAttribute("succes", "Die Bemerkung wurde gelöscht");
			//charger les listes
			

			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			
			request.setAttribute("dossier", dossiers);
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		
		}
		
		
		if(request.getServletPath().equals("/deleteSTdbsh")) {
			String idST = request.getParameter("idST");
			STDAO.deleteSTdbsh(Integer.parseInt(idST));
			request.setAttribute("succes", "Die Bemerkung wurde gelöscht.");
			//charger les listes

			Responsable utilisateurConnecte = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateurConnecte.getId_respo();
			String id= Integer.toString(id_respo);
			Responsable respo = respoDAO.getRespo(id);
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(id_respo);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
		}
		
		
		if(request.getServletPath().equals("/deleteDoc")) {
			String idDossiere = request.getParameter( "id_d" );
			//supprimer le dossier
			dossierDAO.deleteDoc(idDossiere);
			//supprimer les taches du dossier
			tacheDAO.deleteDocTache(idDossiere);
			//supprimer les sous taches du dossier
			STDAO.deleteDocST(idDossiere);
			
			request.setAttribute("succes", "Die Datei wurde gelöscht");
			//charger les listes
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/details")) {
			try {
			String idTache = request.getParameter("tracking");
			List<Tache> tach=tacheDAO.getTache(idTache);
			if(tach.isEmpty()){
				request.setAttribute("erreur", "Keine Datei, die zu dieser Trackingnummer passt ("+idTache+") ");
				request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
			}else{
			request.setAttribute("tache", tach);
			String nom_cl = tach.get(0).getD_nom_cl();
			int id_doc = tach.get(0).getId_doc();
			
			int numTacheEnCour = tacheDAO.getNTEC(id_doc);
			
			int maxTache = tach.size();
			Tache tachValid = null;
			if(numTacheEnCour == maxTache){
				tachValid = tach.get(maxTache-1);
			}else{
			tachValid = tacheDAO.tacheValide(id_doc);
			}
			String dateValid = tachValid.getDateFin();
			String dateFin = tacheDAO.getDateDerniereTache(id_doc);
			String dateDebut = tacheDAO.getDatePremiereTache(id_doc);
			int totalTime=tacheDAO.DateDifference(dateDebut, dateFin);
			int left = tacheDAO.DateDifference(dateValid, dateFin);
			int elapsed = tacheDAO.DateDifference(dateDebut, dateValid);
//			int totalTime=left+elapsed;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dat = simpleDateFormat.format(new Date());
			request.setAttribute("nom_cl", nom_cl);
			request.setAttribute("id_doc", id_doc);
			request.setAttribute("dateFin", dateFin);
			request.setAttribute("dateDebut", dateDebut);
			request.setAttribute("totalTime", totalTime);
			request.setAttribute("left", left);
			request.setAttribute("elapsed", elapsed);
			request.setAttribute("date", dat);
			request.setAttribute("maxTache", maxTache);
			request.setAttribute("numTacheEnCour", numTacheEnCour); 
			Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null){
			int id_client = utilisateurConnecte.getId_client();
			boolean prem = utilisateurConnecte.isPremium();
			request.setAttribute("id_client", id_client); 
			request.setAttribute("prem", prem); 	
			boolean saved = savedDAO.exist(id_client,id_doc);
			request.setAttribute("saved", saved); 
			request.getRequestDispatcher("WEB-INF/details.jsp").forward(request, response);
			}else{

				request.getRequestDispatcher("WEB-INF/details.jsp").forward(request, response);
			}
			}
		}catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
			}
		
		
		if(request.getServletPath().equals("/progression")) {
			try {
			String idTache = request.getParameter("tracking");
			List<Tache> tach=tacheDAO.getTache(idTache);
			if(tach.isEmpty()){
				request.setAttribute("erreur", "Keine Datei, die zu dieser Trackingnummer passt ("+idTache+") ");
				request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
			}else{
			request.setAttribute("tache", tach);
			String nom_cl = tach.get(0).getD_nom_cl();
			int id_doc = tach.get(0).getId_doc();
			
			int numTacheEnCour = tacheDAO.getNTEC(id_doc);
			
			int maxTache = tach.size();
			Tache tachValid = null;
			if(numTacheEnCour == maxTache){
				tachValid = tach.get(maxTache-1);
			}else{
			tachValid = tacheDAO.tacheValide(id_doc);
			}
			String dateValid = tachValid.getDateFin();
			String dateFin = tacheDAO.getDateDerniereTache(id_doc);
			String dateDebut = tacheDAO.getDatePremiereTache(id_doc);
			int totalTime=tacheDAO.DateDifference(dateDebut, dateFin);
			int left = tacheDAO.DateDifference(dateValid, dateFin);
			int elapsed = tacheDAO.DateDifference(dateDebut, dateValid);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dat = simpleDateFormat.format(new Date());
			request.setAttribute("nom_cl", nom_cl);
			request.setAttribute("id_doc", id_doc);
			request.setAttribute("dateFin", dateFin);
			request.setAttribute("dateDebut", dateDebut);
			request.setAttribute("totalTime", totalTime);
			request.setAttribute("left", left);
			request.setAttribute("elapsed", elapsed);
			request.setAttribute("date", dat);
			request.setAttribute("maxTache", maxTache);
			request.setAttribute("numTacheEnCour", numTacheEnCour); 
			Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null){
			int id_client = utilisateurConnecte.getId_client();
			boolean prem = utilisateurConnecte.isPremium();
			request.setAttribute("id_client", id_client); 
			request.setAttribute("prem", prem); 	
			boolean saved = savedDAO.exist(id_client,id_doc);
			request.setAttribute("saved", saved); 
			request.getRequestDispatcher("WEB-INF/prog.jsp").forward(request, response);
			}else{

				request.getRequestDispatcher("WEB-INF/prog.jsp").forward(request, response);
			}
			}
		}catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
			}
		

		if(request.getServletPath().equals("/deleteRespo")) {
			String idRespo = request.getParameter( "id_r" );
			respoDAO.deleteRespo(idRespo);
			Responsable resp = respoDAO.getRespo(idRespo);
			request.setAttribute("succes", "Die verantwortliche Person wurde entfernt.");
			//charger les listes
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
}
		
		if(request.getServletPath().equals("/editRespo")) {
			String idRespo = request.getParameter( "id_r" );
			Responsable respo = respoDAO.getRespo(idRespo);
			request.setAttribute("responsable", respo);
			request.getRequestDispatcher("WEB-INF/editRespo.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/editClient")) {
			String idClient = request.getParameter( "id_r" );
			Client clyan = clientDAO.getClient(idClient);
			request.setAttribute("client", clyan);
			String email = clientmailDAO.getMailClient(Integer.parseInt(idClient));
			request.setAttribute("email", email);
			request.getRequestDispatcher("WEB-INF/editClient.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/deleteClient")) {
			String idClient = request.getParameter( "id_r" );
			//supprimer le client
			clientDAO.deleteClient(idClient);
			//supprimer l'email de client
			clientmailDAO.deleteEmail(idClient);
			
			request.setAttribute("succes", "Der Kunde wurde gelöscht");
			//charger les listes
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/editTache")) {
			String idTache = request.getParameter( "id_d" );
			Tache tachtacha = tacheDAO.getOneOneTache(idTache);
			request.setAttribute("tache", tachtacha);
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			request.getRequestDispatcher("WEB-INF/editTache.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/editDoc")) {
			String idDoc = request.getParameter( "id_d" );
			Dossier doss = dossierDAO.getDoc(idDoc);
			request.setAttribute("dossier", doss);
			request.getRequestDispatcher("WEB-INF/editDoc.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/next")) {
		
			String idDoc = request.getParameter( "id_doc" );
			String idTache = request.getParameter( "id_tache" );
			String date_Debut = request.getParameter( "date_debut" );
			
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int idR = utilisateur.getId_respo();
			int idT=Integer.parseInt(idTache);
			int idD=Integer.parseInt(idDoc);
			
			//methode responsible bach tji la tache li mor hadi ( selon date de debut )
			List<Tache> taches =  tacheDAO.ListTachesAAprouveDossier(idT,idD, date_Debut);
			// Incrementation du numTacheEnCours de la tache
			Tache tachtacha = tacheDAO.getOneOneTache(idT);
			// turn = false
			tacheDAO.UpdateTurn(idT,false,true);
			int NTEC = tachtacha.getD_numTacheEnCour();
			int x = ++ NTEC;
			if(taches.isEmpty()) {
				request.setAttribute("succes", "Die letzte Aufgabe des Dossiers "+idD+" ist erfolgreich abgeschlossen worden.");
				tacheDAO.UpdateNTEC(idT,x);
				String RECIPIENT = clientmailDAO.getMail(idD);
				String USER_NAME = "Tracking.GmbH"; 
				 String PASSWORD = "Bachelor."; 

				        String from = USER_NAME;
				        String pass = PASSWORD;
				        String[] to = { RECIPIENT };
				        String subject = "Bekanntmachung . ";
				        String body = "Guten Tag Herr. '"+tachtacha.getD_nom_cl()+"'. Ihre Akte ist fertig!.     Team WEBTRACK";
				        
				respoDAO.sendFromGMail(from, pass, to, subject, body);
				
				String message = "Le traitement du Dossier de Mr."+tachtacha.getD_nom_cl()+" est Terminé";

				Calendar rightNow = Calendar.getInstance();
				int hour = rightNow.get(Calendar.HOUR_OF_DAY);
				int minute = rightNow.get(Calendar.MINUTE);
				String Uhr= "h";
				String hr = String.valueOf(hour);
				String min = String.valueOf(minute);
				if(minute<10){
					Uhr= hr+":0"+min;
				}else{
					Uhr= hr+":"+min;
				}
				Messenger msg = new Messenger(idD,0,message, null,Uhr);
				messageDAO.addMsgRespo(msg);
			} else {
			Tache tachtachatacha = taches.get(0);
			int idT2 = tachtachatacha.getId_tache();
			tacheDAO.UpdateNTEC(idT2,x); 
			//flux du tache entre les respos
			tacheDAO.UpdateTurn(idT,false,true);
			tacheDAO.UpdateTurn(idT2,true,false);
			request.setAttribute("succes", "Verarbeitung der Datei ("+idD+") ausgefüllt");
			//Bekanntmachung de reception
			Responsable nextRespo = respoDAO.getRespo(Integer.toString(tachtachatacha.getId_respo()));
			String RECIPIENT = nextRespo.getEmail();
			String USER_NAME = "Tracking.GmbH"; 
			 String PASSWORD = "Bachelor."; 

			        String from = USER_NAME;
			        String pass = PASSWORD;
			        String[] to = { RECIPIENT };
			        String subject = "Bekanntmachung";
			        String body = "Sie haben einen neuen Fleck vom Typ '"+tachtachatacha.getD_type()+"' die genehmigt und abgeschlossen werden müssen. Letzter Termin am '"+tachtachatacha.getDateFin()+"' .";
			        
			respoDAO.sendFromGMail(from, pass, to, subject, body);
			String message = "Der Verantworliche "+ utilisateur.getNom() +"hat den Fleck "+idT+" beendet ";

			Calendar rightNow = Calendar.getInstance();
			int hour = rightNow.get(Calendar.HOUR_OF_DAY);
			int minute = rightNow.get(Calendar.MINUTE);
			String Uhr= "h";
			String hr = String.valueOf(hour);
			String min = String.valueOf(minute);
			if(minute<10){
				Uhr= hr+":0"+min;
			}else{
				Uhr= hr+":"+min;
			}
			Messenger msg = new Messenger(idD,0,message, null,Uhr);
			messageDAO.addMsgRespo(msg);
			}
			//stocker cette tache dans une table specifier pour stocker les taches terminé pour chaque 
			// respo (pour les afficher comme taches finis dans fnins.jsp) 
			Tache tacha = tacheDAO.getOneTache(idR,idT);
			endedDAO.add(tacha); 
			
			
			//chargement de liste Dossier
			Responsable respo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(idR);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
			
	//		System.out.println("id tache = " + idTache);
		}
		
		if(request.getServletPath().equals("/approuve")) {
			String idTache = request.getParameter( "id_tache" );
			int idT=Integer.parseInt(idTache);
			Tache tachtacha = tacheDAO.getOneOneTache(idT);
			int idD=tachtacha.getId_doc();
			tacheDAO.UpdateValide(idT);
			request.setAttribute("succes", "Vous avez approuvé le Dossier ("+tachtacha.getId_doc()+")");
			request.setAttribute("erreur", "Rappel: Dernier délai: ("+tachtacha.getDateFin()+")");
			//chargement du responsable de la session et le envoyer 
			Responsable utilisateur = (Responsable) request.getSession().getAttribute("session");
			int idR = utilisateur.getId_respo();
			Responsable respo = respoDAO.getRespo(String.valueOf(idR));
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(idR);
			request.setAttribute("tache", tach);
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			String message = "Der Verantwortliche "+ utilisateur.getNom() +" hat die Aufgabe : "+idT+" genehmigt";

			Calendar rightNow = Calendar.getInstance();
			int hour = rightNow.get(Calendar.HOUR_OF_DAY);
			int minute = rightNow.get(Calendar.MINUTE);
			String Uhr= "h";
			String hr = String.valueOf(hour);
			String min = String.valueOf(minute);
			if(minute<10){
				Uhr= hr+":0"+min;
			}else{
				Uhr= hr+":"+min;
			}
			Messenger msg = new Messenger(idD,0,message, null,Uhr);
			messageDAO.addMsgRespo(msg);
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
		}
		
		if(request.getServletPath().equals("/rollback")) {
			String idDoc = request.getParameter( "id_doc" );
			String idTache = request.getParameter( "id_tache" );
			String date_Debut = request.getParameter( "date_debut" );
			
			request.setAttribute("idDoc", idDoc);
			request.setAttribute("idTache",idTache);
			request.setAttribute("date_Debut",date_Debut);

			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			int id_respooo = utilisateurrrr.getId_respo();
			request.setAttribute("responsable", utilisateurrrr);
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER

			
			request.getRequestDispatcher("WEB-INF/reclamer.jsp").forward(request, response);	
	}
		
	} catch (Exception e) {
		request.setAttribute("erreur", "Es wurde ein Fehler gemacht.");
		System.out.println("error do get : "+e.getMessage());

		//request.getRequestDispatcher("WEB-INF/404.jsp").forward(request, response);
	}
		
		}
	
//-----------------------------------------------------------------------------------------------------------------------------------------
 
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			session=request.getSession();
			if(request.getServletPath().equals("/setNewPassword")) {
				System.out.println("daa");
				String id=(String) session.getAttribute("key");
				System.out.println("id2="+id);
				String pass1=request.getParameter("pass1");
				String pass2=request.getParameter("pass2");
				System.out.println("pass1:"+pass1);
				System.out.println("pass2:"+pass2);
				if(pass1.equals(pass2)) {
					System.out.println("the same");
					Client cl = clientDAO.getClient(id);
					System.out.println(cl.getEmail());
					System.out.println("debug2");
					System.out.println("debug3");
					clientDAO.editClient(cl.getId_client(), cl.getNom(), pass1, cl.getAdresse(),cl.getTel());
					System.out.println("edited");
					request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
				}
			}
			if(request.getServletPath().equals("/sendrecovery")) {
				String email=request.getParameter("emailrecovery");
				System.out.println(email);
				
				if(clientDAO.exist(email)) {
					System.out.println("email trouve");
					//String password=clientmailDAO.revers(clientDAO.getpassword(email));
					Client cl =clientDAO.getClientByEmail(email);
					int key=cl.getId_client();
					request.setAttribute("key", key);
					System.out.println("key="+key);
					String subject="Passwort Trackin Service zurücksetzen";
					String key2=key+"&email="+email;
					String link="http://localhost:8080/Tracking/resetPassword?key="+key+"&email="+email;
					String body="Hallo,\r\n"+
					"Um Ihr Passwort zurückzusetzen, klicken Sie auf den unten stehenden Link\r\n"+
					"http://localhost:8080/Tracking/resetPassword?key="+key+
					"&email="+email;
					String nameClient=cl.getNom();
					String body3="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" + 
							"<html style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\">\r\n" + 
							" <head> \r\n" + 
							"  <meta charset=\"UTF-8\"> \r\n" + 
							"  <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> \r\n" + 
							"  <meta name=\"x-apple-disable-message-reformatting\"> \r\n" + 
							"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> \r\n" + 
							"  <meta content=\"telephone=no\" name=\"format-detection\"> \r\n" + 
							"  <title>Nouveau modèle de courrier électronique 2020-05-12</title> \r\n" + 
							"  <!--[if (mso 16)]>\r\n" + 
							"    <style type=\"text/css\">\r\n" + 
							"    a {text-decoration: none;}\r\n" + 
							"    </style>\r\n" + 
							"    <![endif]--> \r\n" + 
							"  <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--> \r\n" + 
							"  <style type=\"text/css\">\r\n" + 
							"@media only screen and (max-width:600px) {p, ul li, ol li, a { font-size:16px!important; line-height:150%!important } h1 { font-size:20px!important; text-align:center; line-height:120%!important } h2 { font-size:16px!important; text-align:left; line-height:120%!important } h3 { font-size:20px!important; text-align:center; line-height:120%!important } h1 a { font-size:20px!important } h2 a { font-size:16px!important; text-align:left } h3 a { font-size:20px!important } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:10px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:12px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:block!important } a.es-button { font-size:14px!important; display:block!important; border-left-width:0px!important; border-right-width:0px!important } .es-btn-fw { border-width:10px 0px!important; text-align:center!important } .es-adaptive table, .es-btn-fw, .es-btn-fw-brdr, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0px!important } .es-m-p0r { padding-right:0px!important } .es-m-p0l { padding-left:0px!important } .es-m-p0t { padding-top:0px!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } .es-desk-menu-hidden { display:table-cell!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } }\r\n" + 
							"#outlook a {\r\n" + 
							"	padding:0;\r\n" + 
							"}\r\n" + 
							".ExternalClass {\r\n" + 
							"	width:100%;\r\n" + 
							"}\r\n" + 
							".ExternalClass,\r\n" + 
							".ExternalClass p,\r\n" + 
							".ExternalClass span,\r\n" + 
							".ExternalClass font,\r\n" + 
							".ExternalClass td,\r\n" + 
							".ExternalClass div {\r\n" + 
							"	line-height:100%;\r\n" + 
							"}\r\n" + 
							".es-button {\r\n" + 
							"	mso-style-priority:100!important;\r\n" + 
							"	text-decoration:none!important;\r\n" + 
							"}\r\n" + 
							"a[x-apple-data-detectors] {\r\n" + 
							"	color:inherit!important;\r\n" + 
							"	text-decoration:none!important;\r\n" + 
							"	font-size:inherit!important;\r\n" + 
							"	font-family:inherit!important;\r\n" + 
							"	font-weight:inherit!important;\r\n" + 
							"	line-height:inherit!important;\r\n" + 
							"}\r\n" + 
							".es-desk-hidden {\r\n" + 
							"	display:none;\r\n" + 
							"	float:left;\r\n" + 
							"	overflow:hidden;\r\n" + 
							"	width:0;\r\n" + 
							"	max-height:0;\r\n" + 
							"	line-height:0;\r\n" + 
							"	mso-hide:all;\r\n" + 
							"}\r\n" + 
							".es-button-border:hover a.es-button {\r\n" + 
							"	background:#ffffff!important;\r\n" + 
							"	border-color:#ffffff!important;\r\n" + 
							"}\r\n" + 
							".es-button-border:hover {\r\n" + 
							"	background:#ffffff!important;\r\n" + 
							"	border-style:solid solid solid solid!important;\r\n" + 
							"	border-color:#3d5ca3 #3d5ca3 #3d5ca3 #3d5ca3!important;\r\n" + 
							"}\r\n" + 
							"</style> \r\n" + 
							" </head> \r\n" + 
							" <body style=\"width:100%;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0;\"> \r\n" + 
							"  <div class=\"es-wrapper-color\" style=\"background-color:#FAFAFA;\"> \r\n" + 
							"   <!--[if gte mso 9]>\r\n" + 
							"			<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\r\n" + 
							"				<v:fill type=\"tile\" color=\"#fafafa\"></v:fill>\r\n" + 
							"			</v:background>\r\n" + 
							"		<![endif]--> \r\n" + 
							"   <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;\"> \r\n" + 
							"     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"      <td valign=\"top\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \r\n" + 
							"         <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\"> \r\n" + 
							"             <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"              <td align=\"left\" style=\"padding:10px;Margin:0;\"> \r\n" + 
							"               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"580\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" class=\"es-infoblock\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC;\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:12px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:14px;color:#CCCCCC;\"><a href=\"\" class=\"view\" target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:12px;text-decoration:none;color:#CCCCCC;\">View in browser</a></p></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table></td> \r\n" + 
							"             </tr> \r\n" + 
							"           </table></td> \r\n" + 
							"         </tr> \r\n" + 
							"       </table> \r\n" + 
							"       <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-header\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top;\"> \r\n" + 
							"         <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"          <td class=\"es-adaptive\" align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"           <table class=\"es-header-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#3D5CA3;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#3d5ca3\" align=\"center\"> \r\n" + 
							"             <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"              <td style=\"Margin:0;padding-top:20px;padding-bottom:20px;padding-left:20px;padding-right:20px;background-color:#3D5CA3;\" bgcolor=\"#3d5ca3\" align=\"left\"> \r\n" + 
							"               <!--[if mso]><table width=\"560\" cellpadding=\"0\" \r\n" + 
							"                        cellspacing=\"0\"><tr><td width=\"270\" valign=\"top\"><![endif]--> \r\n" + 
							"               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td class=\"es-m-p20b\" width=\"270\" align=\"left\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td class=\"es-m-p0l es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;font-size:0px;\"><a href=\"https://viewstripo.email\" target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:none;color:#1376C8;\"><img src=\"https://ifnafg.stripocdn.email/content/guids/CABINET_5f977935d03ec23aea3f6ca7e7e19956/images/55401589297216876.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\" width=\"183\"></a></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table> \r\n" + 
							"               <!--[if mso]></td><td width=\"20\"></td><td width=\"270\" valign=\"top\"><![endif]--> \r\n" + 
							"               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"270\" align=\"left\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td class=\"es-m-txt-c\" align=\"right\" style=\"padding:0;Margin:0;padding-top:10px;\"><span class=\"es-button-border\" style=\"border-style:solid;border-color:#3D5CA3;background:#FFFFFF;border-width:2px;display:inline-block;border-radius:10px;width:auto;\"><a href=\"http://localhost:8080/Tracking/index.html\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:14px;color:#3D5CA3;border-style:solid;border-color:#FFFFFF;border-width:15px 20px 15px 20px;display:inline-block;background:#FFFFFF;border-radius:10px;font-weight:bold;font-style:normal;line-height:17px;width:auto;text-align:center;\">Zuhause</a></span></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table> \r\n" + 
							"               <!--[if mso]></td></tr></table><![endif]--></td> \r\n" + 
							"             </tr> \r\n" + 
							"           </table></td> \r\n" + 
							"         </tr> \r\n" + 
							"       </table> \r\n" + 
							"       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \r\n" + 
							"         <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"          <td style=\"padding:0;Margin:0;background-color:#FAFAFA;\" bgcolor=\"#fafafa\" align=\"center\"> \r\n" + 
							"           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#FFFFFF;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\"> \r\n" + 
							"             <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"              <td style=\"padding:0;Margin:0;padding-left:20px;padding-right:20px;padding-top:40px;background-color:transparent;background-position:left top;\" bgcolor=\"transparent\" align=\"left\"> \r\n" + 
							"               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"560\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:left top;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px;font-size:0;\"><img src=\"https://ifnafg.stripocdn.email/content/guids/CABINET_dd354a98a803b60e2f0411e893c82f56/images/23891556799905703.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\" width=\"175\"></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:15px;padding-bottom:15px;\"><h1 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#333333;\"><strong>VERGESSEN SIE IHRE </strong></h1><h1 style=\"Margin:0;line-height:24px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:20px;font-style:normal;font-weight:normal;color:#333333;\"><strong>&nbsp;PASSWORD?</strong></h1></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" style=\"padding:0;Margin:0;padding-left:40px;padding-right:40px;\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:24px;color:#666666;\">HALLO,"+nameClient+"</p></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" style=\"padding:0;Margin:0;padding-right:35px;padding-left:40px;\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:24px;color:#666666;\">Es wurde darum gebeten, Ihr Passwort zu ändern!</p></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" style=\"padding:0;Margin:0;padding-top:25px;padding-left:40px;padding-right:40px;\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:24px;color:#666666;\">Wenn Sie diese Anfrage nicht gestellt haben, ignorieren Sie diese E-Mail einfach. Andernfalls klicken Sie bitte auf die Schaltfläche unten, um Ihr Passwort zu ändern:</p></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" style=\"Margin:0;padding-left:10px;padding-right:10px;padding-top:40px;padding-bottom:40px;\"><span class=\"es-button-border\" style=\"border-style:solid;border-color:#3D5CA3;background:#FFFFFF;border-width:2px;display:inline-block;border-radius:10px;width:auto;\"><a href=\""+link+"\" class=\"es-button\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:14px;color:#3D5CA3;border-style:solid;border-color:#FFFFFF;border-width:15px 20px 15px 20px;display:inline-block;background:#FFFFFF;border-radius:10px;font-weight:bold;font-style:normal;line-height:17px;width:auto;text-align:center;\">Passwort zurücksetzen</a></span></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table></td> \r\n" + 
							"             </tr> \r\n" + 
							"             <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"              <td style=\"padding:0;Margin:0;padding-left:10px;padding-right:10px;padding-top:20px;background-position:center center;\" align=\"left\"> \r\n" + 
							"               <!--[if mso]><table width=\"580\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"199\" valign=\"top\"><![endif]--> \r\n" + 
							"               <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"199\" align=\"left\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td class=\"es-m-txt-c\" align=\"right\" style=\"padding:0;Margin:0;padding-top:15px;\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:16px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:24px;color:#666666;\"><strong>Follow us:</strong></p></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table> \r\n" + 
							"               <!--[if mso]></td><td width=\"20\"></td><td width=\"361\" valign=\"top\"><![endif]--> \r\n" + 
							"               <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"361\" align=\"left\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-position:center center;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td class=\"es-m-txt-c\" align=\"left\" style=\"padding:0;Margin:0;padding-bottom:5px;padding-top:10px;font-size:0;\"> \r\n" + 
							"                       <table class=\"es-table-not-adapt es-social\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                         <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px;\"><img src=\"https://ifnafg.stripocdn.email/content/assets/img/social-icons/rounded-gray/facebook-rounded-gray.png\" alt=\"Fb\" title=\"Facebook\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \r\n" + 
							"                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px;\"><img src=\"https://ifnafg.stripocdn.email/content/assets/img/social-icons/rounded-gray/twitter-rounded-gray.png\" alt=\"Tw\" title=\"Twitter\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \r\n" + 
							"                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px;\"><img src=\"https://ifnafg.stripocdn.email/content/assets/img/social-icons/rounded-gray/instagram-rounded-gray.png\" alt=\"Ig\" title=\"Instagram\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \r\n" + 
							"                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px;\"><img src=\"https://ifnafg.stripocdn.email/content/assets/img/social-icons/rounded-gray/youtube-rounded-gray.png\" alt=\"Yt\" title=\"Youtube\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \r\n" + 
							"                          <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;padding-right:10px;\"><img src=\"https://ifnafg.stripocdn.email/content/assets/img/social-icons/rounded-gray/linkedin-rounded-gray.png\" alt=\"In\" title=\"Linkedin\" width=\"32\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\"></td> \r\n" + 
							"                         </tr> \r\n" + 
							"                       </table></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table> \r\n" + 
							"               <!--[if mso]></td></tr></table><![endif]--></td> \r\n" + 
							"             </tr> \r\n" + 
							"             <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"              <td style=\"Margin:0;padding-top:5px;padding-bottom:20px;padding-left:20px;padding-right:20px;background-position:left top;\" align=\"left\"> \r\n" + 
							"               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"560\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" style=\"padding:0;Margin:0;\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#666666;\">Contact us: <a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:none;color:#666666;\" href=\"tel:123456789\">123456789</a> | <a target=\"_blank\" href=\"mailto:trackingService@mail.com\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:none;color:#666666;\">your@mail.com</a></p></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table></td> \r\n" + 
							"             </tr> \r\n" + 
							"           </table></td> \r\n" + 
							"         </tr> \r\n" + 
							"       </table> \r\n" + 
							"       <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top;\"> \r\n" + 
							"         <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"          <td style=\"padding:0;Margin:0;background-color:#FAFAFA;\" bgcolor=\"#fafafa\" align=\"center\"> \r\n" + 
							"           <table class=\"es-footer-body\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;\"> \r\n" + 
							"             <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"              <td style=\"Margin:0;padding-top:10px;padding-left:20px;padding-right:20px;padding-bottom:30px;background-color:#0B5394;background-position:left top;\" bgcolor=\"#0b5394\" align=\"left\"> \r\n" + 
							"               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"560\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"left\" style=\"padding:0;Margin:0;padding-top:5px;padding-bottom:5px;\"><h2 style=\"Margin:0;line-height:19px;mso-line-height-rule:exactly;font-family:arial, 'helvetica neue', helvetica, sans-serif;font-size:16px;font-style:normal;font-weight:normal;color:#FFFFFF;\"><strong>Have quastions?</strong></h2></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"left\" style=\"padding:0;Margin:0;padding-bottom:5px;\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#FFFFFF;\">We are here to help, learn more about us <a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:none;color:#FFFFFF;\" href=\"\">here</a></p><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:14px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:21px;color:#FFFFFF;\">or <a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:14px;text-decoration:none;color:#FFFFFF;\" href=\"\">contact us</a><br></p></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table></td> \r\n" + 
							"             </tr> \r\n" + 
							"           </table></td> \r\n" + 
							"         </tr> \r\n" + 
							"       </table> \r\n" + 
							"       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \r\n" + 
							"         <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"          <td style=\"padding:0;Margin:0;background-color:#FAFAFA;\" bgcolor=\"#fafafa\" align=\"center\"> \r\n" + 
							"           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\"> \r\n" + 
							"             <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"              <td style=\"padding:0;Margin:0;padding-top:15px;background-position:left top;\" align=\"left\"> \r\n" + 
							"               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"600\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" style=\"padding:0;Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px;font-size:0;\"> \r\n" + 
							"                       <table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                         <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                          <td style=\"padding:0;Margin:0px;border-bottom:1px solid #FAFAFA;background:none;height:1px;width:100%;margin:0px;\"></td> \r\n" + 
							"                         </tr> \r\n" + 
							"                       </table></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table></td> \r\n" + 
							"             </tr> \r\n" + 
							"           </table></td> \r\n" + 
							"         </tr> \r\n" + 
							"       </table> \r\n" + 
							"       <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top;\"> \r\n" + 
							"         <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"          <td style=\"padding:0;Margin:0;background-color:#FAFAFA;\" bgcolor=\"#fafafa\" align=\"center\"> \r\n" + 
							"           <table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"transparent\" align=\"center\"> \r\n" + 
							"             <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"              <td align=\"left\" style=\"Margin:0;padding-bottom:5px;padding-top:15px;padding-left:20px;padding-right:20px;\"> \r\n" + 
							"               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"560\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td align=\"center\" style=\"padding:0;Margin:0;\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-size:12px;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;line-height:18px;color:#666666;\">This Email was sent to you from our company <br> because you ask for password reset.</p></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table></td> \r\n" + 
							"             </tr> \r\n" + 
							"           </table></td> \r\n" + 
							"         </tr> \r\n" + 
							"       </table> \r\n" + 
							"       <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;\"> \r\n" + 
							"         <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"          <td align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"           <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:transparent;\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\"> \r\n" + 
							"             <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"              <td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:30px;padding-bottom:30px;\"> \r\n" + 
							"               <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                 <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                  <td width=\"560\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;\"> \r\n" + 
							"                   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;\"> \r\n" + 
							"                     <tr style=\"border-collapse:collapse;\"> \r\n" + 
							"                      <td class=\"es-infoblock made_with\" align=\"center\" style=\"padding:0;Margin:0;line-height:0px;font-size:0px;color:#CCCCCC;\"><a target=\"_blank\" href=\"https://viewstripo.email/?utm_source=templates&utm_medium=email&utm_campaign=education&utm_content=trigger_newsletter2\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:helvetica, 'helvetica neue', arial, verdana, sans-serif;font-size:12px;text-decoration:none;color:#CCCCCC;\"><img src=\"https://ifnafg.stripocdn.email/content/guids/CABINET_5f977935d03ec23aea3f6ca7e7e19956/images/54401589297424215.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic;\" width=\"125\"></a></td> \r\n" + 
							"                     </tr> \r\n" + 
							"                   </table></td> \r\n" + 
							"                 </tr> \r\n" + 
							"               </table></td> \r\n" + 
							"             </tr> \r\n" + 
							"           </table></td> \r\n" + 
							"         </tr> \r\n" + 
							"       </table></td> \r\n" + 
							"     </tr> \r\n" + 
							"   </table> \r\n" + 
							"  </div>  \r\n" + 
							" </body>\r\n" + 
							"</html>";
							
							
							
							
							
							
					String body2="<p><strong>Hello Dear,</strong></p>\r\n" + 
							"<p><strong>To reset your password, click on the link below</strong></p>\r\n" + 
							"<p><br></p>\r\n" + 
							"<a href=\"http://localhost:8080/Tracking/resetPassword?key="+key2+" class=\"myButton\">Reset your password</a>\r\n" + 
							"<style>\r\n" + 
							".myButton {\r\n" + 
							"  width:80px;\r\n" + 
							"  text-align:center;\r\n" + 
							"	background:linear-gradient(to bottom, #f2136c 5%, #a42abd 100%);\r\n" + 
							"	background-color:#f2136c;\r\n" + 
							"	border-radius:28px;\r\n" + 
							"	border:1px solid #18ab29;\r\n" + 
							"	display:inline-block;\r\n" + 
							"	cursor:pointer;\r\n" + 
							"	color:#ffffff;\r\n" + 
							"	font-family:Arial;\r\n" + 
							"	font-size:17px;\r\n" + 
							"	padding:16px 31px;\r\n" + 
							"	text-decoration:none;\r\n" + 
							"	text-shadow:0px 1px 0px #2f6627;\r\n" + 
							"}\r\n" + 
							".myButton:hover {\r\n" + 
							"	background:linear-gradient(to bottom, #a42abd 5%, #f2136c 100%);\r\n" + 
							"	background-color:#a42abd;\r\n" + 
							"}\r\n" + 
							".myButton:active {\r\n" + 
							"	position:relative;\r\n" + 
							"	top:1px;\r\n" + 
							"}\r\n" + 
							"</style>";
					String USER_NAME = "Tracking.GmbH"; 
					 String PASSWORD = "Bachelor."; 
					 String from = USER_NAME;
					 String pass = PASSWORD;
					respoDAO.sendFromGMail(from, pass,email, subject, body3);
					System.out.println("email sent !!");
					request.setAttribute("sentmsg", "Ein Link zur Passwortwiederherstellung wurde an Ihre E-Mail gesendet. ");
					request.getRequestDispatcher("WEB-INF/forgotpassword.jsp").forward(request, response);

				}
				else {
					System.out.println("email introuvable");
					request.setAttribute("sentmsg", "Email wurde nicht gefunden ");
					request.getRequestDispatcher("WEB-INF/forgotpassword.jsp").forward(request, response);
				}
			}
if(request.getServletPath().equals("/sendrequest")) {
	System.out.println("YALARAW3a");
					String id_doc = request.getParameter("numTache");
					String responame = request.getParameter("namRespo");
					String Clientname = request.getParameter("nomClient");
					System.out.println(Clientname);
					String type =request.getParameter("typedoc");
					String date =request.getParameter("datedoc");
					System.out.println("YALARAW3a wa hya");
					System.out.println(id_doc);
					AdditionalDoc doc=new AdditionalDoc();
					doc.setDone(false);
					doc.setId_respo(responame);
					doc.setId_client(Clientname);
					doc.setId_tache(Integer.parseInt(id_doc));
					doc.setType(type);
					doc.setDate(date);
					additionalDocDAO.addAdditionalDoc(doc);
					
					

				request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
			}
			if(request.getServletPath().equals("/sendemail")) {
				String emailtab[]=new String[4];
				String email=request.getParameter("clientEmail");
				emailtab[0]=email;
				System.out.println("hi"+emailtab[0]);
				String body=request.getParameter("body");
				String subject=request.getParameter("subject");
				String USER_NAME = "Tracking.GmbH"; 
				 String PASSWORD = "Bachelor."; 
				 String from = USER_NAME;
				 String pass = PASSWORD;
				respoDAO.sendFromGMail(from, pass,email, subject, body);
				System.out.println("email sent");
				response.sendRedirect("http://localhost:8080/Tracking/statistics");
				//request.getRequestDispatche r("WEB-INF/statistics.jsp").forward(request, response);
			}
			if(request.getServletPath().equals("/loginRespo")){
				String exceptionContent = "Bei der Authentifizierung ist ein Fehler aufgetreten.";
				try{
					String email = request.getParameter("emailRespo");
					String password = request.getParameter("passwordRespo");
					
					
						boolean status = false;
						status=respoDAO.login(email, password);
						if(status){
							session = request.getSession();
							Responsable utilisateur = respoDAO.getRespo(email, password);
							respoDAO.online(true, utilisateur.getId_respo());
							session.setAttribute("session", utilisateur);
							List<Tache> tach =  tacheDAO.ListTacheRespo(utilisateur.getId_respo());
							request.setAttribute("responsable", utilisateur);
							request.setAttribute("tache", tach);
							request.setAttribute("succes", "Willkommen "+ utilisateur.getNom() + "! Sie haben sich authentifiziert");
							if(session != null){
							Responsable utilisateurConnecte = (Responsable) request.getSession().getAttribute("session");
							int id_respoo = utilisateurConnecte.getId_respo();
							respoDAO.online(true, id_respoo);
							List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respoo);
							request.setAttribute("tachApr", tachApr);
							List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respoo);
							request.setAttribute("tachEC", tachEC);
							List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respoo);
							request.setAttribute("tachProg", tachProg);
							int totalAp = tacheDAO.TotalApprouve(id_respoo);
							int totalEn = tacheDAO.TotalEnCours(id_respoo);
							//---
							for (Tache tache : tachProg) {
								int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
								int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
								tache.setTotalTache(totalTache);
								tache.setMaxTache(maxTache);
							}
							
							request.setAttribute("totalAp", totalAp);
							request.setAttribute("totalEn", totalEn);
							//fin liste HEADER
							}
							request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);

						}
						else{
						exceptionContent = "falsches Login oder Passwort";
						throw new Exception(exceptionContent);
						}
					
				} catch (Exception e) {
					request.setAttribute("erreur", exceptionContent);	
					request.getRequestDispatcher("WEB-INF/loginRespo.jsp").forward(request, response);
				
				}
			}
			if(request.getServletPath().equals("/loginAdmin")) {
				session=request.getSession();
				String email = request.getParameter("emailAdmin");
				String password = request.getParameter("passwordAdmin");
				Admin admin =adminDao.getAdmin(email, password);
				if(adminDao.loginAdmin(email, password)) {
					session.setAttribute("admin",admin);
				System.out.println("input login ="+email+"  "+password);
				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respo);
				}
				/*
				 * statistics part
				 */

				request.setAttribute("admin", request.getAttribute("admin"));
				Graphinfo.count=0;
				int total=0;
				int nbrDossier=dossierDAO.ListDossier().size();
				int nbrResponsable=respoDAO.ListRespo().size();
				int registredClient=clientDAO.ListClient().size();
				int totalClient = dossierDAO.getAllclient();
				List<Graphinfo> infos= graphinfoDAO.infos(4);
				List<AdditionalDoc> additional=additionalDocDAO.additionalDoc();
				request.setAttribute("admin",session.getAttribute("admin"));
				for(Graphinfo d :infos) {
					System.out.println("data :"+d.toString());
					total+=d.getOperations();
				}
				System.out.println("total: "+total);
				for(Graphinfo d :infos) {
					d.setHeight(d.getOperations()*300/10);
					System.out.println("height: "+d.getHeight());
				}
				
				
				/*tab filling
				 * end graphs
				 */
				List<Responsable>respo=respoDAO.ListRespo();
				
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String emailc = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(emailc);
					System.out.println(client.toString());
				}
				request.setAttribute("client", clyan);		
				
				request.setAttribute("graphData", infos);
				request.setAttribute("total", total);
				request.setAttribute("nbrDossier",nbrDossier);
				request.setAttribute("nbrResponsable",nbrResponsable);
				request.setAttribute("nbrClient",totalClient);
				request.setAttribute("registredClient",registredClient);
				request.setAttribute("dossier", dossiers);
				request.setAttribute("responsables", respo);
				request.setAttribute("additional", additional);
				request.getRequestDispatcher("WEB-INF/statistics.jsp").forward(request, response);	
			
			}
			else {
					request.getRequestDispatcher("WEB-INF/loginadmin.jsp").forward(request, response);

				}
			}
		if(request.getServletPath().equals("/loginClient")) {
			String exceptionContent = "Bei der Authentifizierung ist ein Fehler aufgetreten.";
			try{
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				boolean status = false;
				status=clientDAO.login(email, password);
				if(status){
					session = request.getSession();
					int idC=clientmailDAO.getIdCM(email);
					String id =String.valueOf(idC);
					Client utilisateur = clientDAO.getClient(id, password);
					session.setAttribute("session", utilisateur);
					String nom_cl = utilisateur.getNom();
					List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
					for (Dossier dossier : dossiers) {
						String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
						String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
						int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
						int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
						int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
						String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
						dossier.setDateDebut(dateDebut);
						dossier.setDateFin(dateFin);
						dossier.setTotalTache(totalTache);
						dossier.setMaxTache(maxTache);
						dossier.setDureeTotal(dureeTotal);
						dossier.setRespo(respo);
						
					}
					request.setAttribute("client", utilisateur);
					request.setAttribute("dossier", dossiers);
					request.setAttribute("succes", "Willkommen "+ utilisateur.getNom() + "! Sie haben sich authentifiziert"); 
					request.getRequestDispatcher("WEB-INF/acceuilClient.jsp").forward(request, response);

				}
				else{
				exceptionContent = "login / mdp incorrect";
				throw new Exception(exceptionContent);
				}
			
			} catch (Exception e) {
				request.setAttribute("erreur", exceptionContent);	
				request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
			
			}
		}
		
		if(request.getServletPath().equals("/messenger")) {
			String envoye = request.getParameter( "msg" );
			String id_respo = request.getParameter( "id_respo" );
			String id_doc = request.getParameter( "id_doc" );
			Blob a = null;
//			System.out.println(envoye+"/"+ id_respo+"/"+id_doc);
			Calendar rightNow = Calendar.getInstance();
			int hour = rightNow.get(Calendar.HOUR_OF_DAY);
			int minute = rightNow.get(Calendar.MINUTE);
			String Uhr= "h";
			String hr = String.valueOf(hour);
			String min = String.valueOf(minute);
			if(minute<10){
				Uhr= hr+":0"+min;
			}else{
				Uhr= hr+":"+min;
			}
			Messenger msg = new Messenger(Integer.parseInt(id_doc),Integer.parseInt(id_respo),envoye, a,Uhr);
			messageDAO.addMsgRespo(msg);
			int idD = Integer.parseInt(id_doc);
			//---------------------------------------------------------------------------
			List<Messenger> mess =  messageDAO.ListMsg(idD);
			for (Messenger messenger : mess) {
				int idrr=messenger.getId_respo();
				Responsable respo = respoDAO.getRespo(String.valueOf(idrr));
				String nom_respo=respo.getNom();
				messenger.setNom_respo(nom_respo);
			}

			int id_respooo = Integer.parseInt(id_respo);
			List<Responsable> respo =  respoDAO.ListRespoMessage(idD);
			request.setAttribute("respo", respo);	
			request.setAttribute("messenger", mess);
			request.setAttribute("idD", id_doc);
			request.setAttribute("id", id_respooo);
			
			//chargement de liste Dossier
			Responsable respoo = respoDAO.getRespo(String.valueOf(id_respooo));
			request.setAttribute("responsable", respoo);
			//Header listes
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			
			request.getRequestDispatcher("WEB-INF/messenger.jsp").forward(request, response);
		}
	
		
		if(request.getServletPath().equals("/rollback")) {
			String idDoc = request.getParameter( "idDoc" );
			String idTache = request.getParameter( "idTache" );
			String date_Debut = request.getParameter( "date_Debut" );
			String motif = request.getParameter("motif");
			int idT=Integer.parseInt(idTache);
			int idD=Integer.parseInt(idDoc);


			//methode responsible bach tji la tache li 9bel  hadi ( selon date de debut )
			List<Tache> taches =  tacheDAO.ListTachesRollback(idT,idD, date_Debut);
			if(taches.isEmpty()) {
				request.setAttribute("erreur", "Kein früherer Verantwortlicher für diese Aufgabe");
			} else {
			tacheDAO.UpdateRollback(idT);
			Tache tacha = taches.get(0);
			int idT2 = tacha.getId_tache();
			//Enregistrer le motif comme Remarque/Sous-tache de la tache precedente
			SousTache ST = new SousTache(idT2,motif);
			
			STDAO.addST(ST);
			STDAO.deleteST(idT2);
			//flux du tache entre les respos
			tacheDAO.UpdateTurn(idT,false,false);
			tacheDAO.UpdateTurn(idT2,true,false);
			request.setAttribute("succes", "Behauptung aufgestellt!");
			//ajout de 2 jours
			String dateFin = tacheDAO.getDateDerniereTache(idD);
			Tache akhirTache = tacheDAO.getLastTache(dateFin);
			int idAkhirTache = akhirTache.getId_tache();
			String dt = akhirTache.getDateFin();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(dt));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			c.add(Calendar.DATE, 2);  
			dt = sdf.format(c.getTime()); 
			tacheDAO.editDateFin(idAkhirTache,dt);
			//Bekanntmachung de retour
			Responsable previousRespo = respoDAO.getRespo(Integer.toString(tacha.getId_respo()));
			String RECIPIENT = previousRespo.getEmail();
			String USER_NAME = "Tracking.GmbH"; 
			 String PASSWORD = "Bachelor."; 
			 
			        String from = USER_NAME;
			        String pass = PASSWORD;
			        String[] to = { RECIPIENT };
			        String subject = "Bekanntmachung . ";
			        String body = "Bonjour Mr. "+previousRespo.getNom()+".Der Verantwortliche für die folgende Aufgabe '"+tacha.getLibelle()+"' ließ sie zurückkehren  "
			        		+ " aus dem Grund: '"+motif+"'. Letzter Termin am '"+tacha.getDateFin()+"'.";
			        
			respoDAO.sendFromGMail(from, pass, to, subject, body);
			//------  Client
			int idD2 = tacha.getId_doc();
			String email2 = clientmailDAO.getMail(idD2);
			String[] to2 = { email2 };
			String subject2 = "Bekanntmachung";
	        String body2 = "Guten Tag Herr. "+tacha.getD_nom_cl()+". Es wird eine Verzögerung bei der Verarbeitung Ihrer Datei vom Typ '"+tacha.getD_type()+
	        		"'. Bitte nehmen Sie sich einen Zeitraum von 2 Tagen, der zum Enddatum der erwarteten Behandlung hinzukommt. '"+tacha.getDateFin()+"'"
	        				+ ". Wir danken Ihnen für Ihr Verständnis.           Team WEBTRACK";
	        

	Tache tachtacha = tacheDAO.getOneOneTache(idTache);
	int id_resp = tachtacha.getId_respo();
	Responsable respo = respoDAO.getRespo(String.valueOf(id_resp));
	String message = "Der Manager "+ respo.getNom() +" drehte den Fleck dreht den Fleck von Herr."+tacha.getD_nom_cl()+"  aus dem Grund: '"+motif+"'";
	Calendar rightNow = Calendar.getInstance();
	int hour = rightNow.get(Calendar.HOUR_OF_DAY);
	int minute = rightNow.get(Calendar.MINUTE);
	String Uhr= "h";
	String hr = String.valueOf(hour);
	String min = String.valueOf(minute);
	if(minute<10){
		Uhr= hr+":0"+min;
	}else{
		Uhr= hr+":"+min;
	}
	Messenger msg = new Messenger(idD2,0,message, null,Uhr);
	messageDAO.addMsgRespo(msg);
			}
			
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			request.setAttribute("responsable", utilisateurrrr);
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tache", tach);
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);	
	}
		
		if(request.getServletPath().equals("/progression")) {
			try {
			String idTache = request.getParameter("tracking");
			List<Tache> tach=tacheDAO.getTache(idTache);
			if(tach.isEmpty()){
				request.setAttribute("erreur", "Keine Datei, die zu dieser Trackingnummer passt ("+idTache+") ");
				request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
			}else{
			request.setAttribute("tache", tach);
			String nom_cl = tach.get(0).getD_nom_cl();
			int id_doc = tach.get(0).getId_doc();
			
			int numTacheEnCour = tacheDAO.getNTEC(id_doc);
			
			int maxTache = tach.size();
			Tache tachValid = null;
			if(numTacheEnCour == maxTache){
				tachValid = tach.get(maxTache-1);
			}else{
			tachValid = tacheDAO.tacheValide(id_doc);
			}
			String dateValid = tachValid.getDateFin();
			String dateFin = tacheDAO.getDateDerniereTache(id_doc);
			String dateDebut = tacheDAO.getDatePremiereTache(id_doc);
			int totalTime=tacheDAO.DateDifference(dateDebut, dateFin);
			int left = tacheDAO.DateDifference(dateValid, dateFin);
			int elapsed = tacheDAO.DateDifference(dateDebut, dateValid);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dat = simpleDateFormat.format(new Date());
			request.setAttribute("nom_cl", nom_cl);
			request.setAttribute("id_doc", id_doc);
			request.setAttribute("dateFin", dateFin);
			request.setAttribute("dateDebut", dateDebut);
			request.setAttribute("totalTime", totalTime);
			request.setAttribute("left", left);
			request.setAttribute("elapsed", elapsed);
			request.setAttribute("date", dat);
			request.setAttribute("maxTache", maxTache);
			request.setAttribute("numTacheEnCour", numTacheEnCour); 
			Client utilisateurConnecte = (Client) request.getSession().getAttribute("session");
			if(utilisateurConnecte != null){
			int id_client = utilisateurConnecte.getId_client();
			boolean prem = utilisateurConnecte.isPremium();
			request.setAttribute("id_client", id_client); 
			request.setAttribute("prem", prem); 	
			boolean saved = savedDAO.exist(id_client,id_doc);
			request.setAttribute("saved", saved); 
			request.getRequestDispatcher("WEB-INF/prog.jsp").forward(request, response);
			}else{

				request.getRequestDispatcher("WEB-INF/prog.jsp").forward(request, response);
			}
			}
		}catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);
		}
			}
		

		
		if(request.getServletPath().equals("/editProfile")) {
			try {
				String id_client = request.getParameter("id_client");	
				String adresse = request.getParameter("adresse");	
				String tel = request.getParameter("tel");
				String password = request.getParameter("password");
				int id = Integer.parseInt(id_client);
				clientDAO.editProfile(id,adresse,tel,password);
				Client clyan=clientDAO.getClient(id_client);
				request.setAttribute("client", clyan);
				String email = clientmailDAO.getMailClient(clyan.getId_client());
				request.setAttribute("email", email);
				request.setAttribute("succes", "Ihre Daten wurden geändert.");
				request.getRequestDispatcher("WEB-INF/profile.jsp").forward(request, response);
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/editProfile.jsp").forward(request, response);
			} 	
		}
		 
		
		if(request.getServletPath().equals("/ajoutDoc")) {
			try {
				String idDoc = request.getParameter("tracking");	
				String nomCl = request.getParameter("nom_cl");
				String emailCl = request.getParameter("email_cl");
				String type = request.getParameter("type"); 
				
				Dossier nouveauDossier = new Dossier(Integer.parseInt(idDoc),nomCl,type,0);
				dossierDAO.addDossier(nouveauDossier);
				
				Clientmail cm = new Clientmail(Integer.parseInt(idDoc),emailCl);
				clientmailDAO.addCm(cm);
				 


				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respo);
					
				}
				request.setAttribute("dossier", dossiers);	
				List<Responsable> respo =  respoDAO.ListRespo();
				request.setAttribute("responsable", respo);	
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String email = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(email);
				}
				request.setAttribute("client", clyan);	
				request.setAttribute("succes", " Die Ordnernummer "+idDoc+" wurde hinzugefügt." );
				String message = " Der Ordner Nummer "+idDoc+" wird erstellt";
				Calendar rightNow = Calendar.getInstance();
				int hour = rightNow.get(Calendar.HOUR_OF_DAY);
				int minute = rightNow.get(Calendar.MINUTE);
				String Uhr= "h";
				String hr = String.valueOf(hour);
				String min = String.valueOf(minute);
				if(minute<10){
					Uhr= hr+":0"+min;
				}else{
					Uhr= hr+":"+min;
				}
				Messenger msg = new Messenger(Integer.parseInt(idDoc),0,message, null,Uhr);
				messageDAO.addMsgRespo(msg);
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addDoc.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			}	
	}
		
		if(request.getServletPath().equals("/addSTAdmin")) {
			try{
				String id_tache = request.getParameter("id_tache");
				String libelle = request.getParameter("libelle"); 
				
				SousTache nouvelleST = new SousTache(Integer.parseInt(id_tache),libelle);
				STDAO.addST(nouvelleST);
				
				List<SousTache> tachee =  STDAO.ListST(Integer.parseInt(id_tache));
				request.setAttribute("tache", tachee);
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addST.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/viewSTAdmin.jsp").forward(request, response);
			}
	}
		
		if(request.getServletPath().equals("/addST")) {
		try{
			String id_tache = request.getParameter("id_tache");
			String libelle = request.getParameter("libelle"); 
			
			SousTache nouvelleST = new SousTache(Integer.parseInt(id_tache),libelle);
			STDAO.addST(nouvelleST);
			
			//affichage des listes
			Responsable utilisateurConnecte = (Responsable) request.getSession().getAttribute("session");
			int id_respo = utilisateurConnecte.getId_respo();
			String id= Integer.toString(id_respo);
			Responsable respo = respoDAO.getRespo(id);
			request.setAttribute("responsable", respo);
			//liste des taches / dossiers;
			List<Tache> tach =  tacheDAO.ListTacheRespo(id_respo);
			request.setAttribute("tache", tach);
			request.setAttribute("succes", "Ihre Bemerkung wurde gut ergänzt");	
		} catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/addST.jsp").forward(request, response);
		} finally {
			//Header listes
			Responsable utilisateurrrr = (Responsable) request.getSession().getAttribute("session");
			
			int id_respooo = utilisateurrrr.getId_respo();
			List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respooo);
			request.setAttribute("tachApr", tachApr);
			List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respooo);
			request.setAttribute("tachEC", tachEC);
			List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respooo);
			request.setAttribute("tachProg", tachProg);
			int totalAp = tacheDAO.TotalApprouve(id_respooo);
			int totalEn = tacheDAO.TotalEnCours(id_respooo);
			//---
			for (Tache tache : tachProg) {
				int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
				int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
				tache.setTotalTache(totalTache);
				tache.setMaxTache(maxTache);
			}
			
			request.setAttribute("totalAp", totalAp);
			request.setAttribute("totalEn", totalEn);
			//fin liste HEADER
			request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);
		}	
		}
		
		if(request.getServletPath().equals("/ajoutRespo")) {
			try {
				String nom = request.getParameter("nom");
				String email = request.getParameter("email");
				String password = request.getParameter("password");	
				String photo = request.getParameter("photo");
				
				Responsable nouveauRespo = new Responsable(nom, email,password,photo );
				respoDAO.addRespo(nouveauRespo);

				
				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respo);
					
				}
				request.setAttribute("dossier", dossiers);
				List<Responsable> respo =  respoDAO.ListRespo();
				request.setAttribute("responsable", respo);	
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String emaill = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(emaill);
				}
				request.setAttribute("client", clyan);	
				request.setAttribute("succes", "Le Responsable "+nouveauRespo.getNom()+" a été bien ajouté");	
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addRespo.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			}	
	}
		
		if(request.getServletPath().equals("/ajoutTache")) {
			try {
				String id_respofull = request.getParameter("id_respo");
				String id_doc = request.getParameter("id_doc");
				String libelle = request.getParameter("libelle");	
				String dateDebut_ = request.getParameter("dateDebut");
				String dateFin_ = request.getParameter("dateFin");
				Dossier d1 = dossierDAO.getDoc(id_doc);
				String d_nom_cl = d1.getNom_cl();
				String d_type = d1.getType();
				Boolean b = false;
				
				//date difference
				int duree=tacheDAO.DateDifference(dateDebut_, dateFin_);  
				
				int i = id_respofull.indexOf(' ');
				String id_respo = id_respofull.substring(0, i);
				
				
				boolean a = false;
				int id = Integer.parseInt(id_doc);
				int deutschland = tacheDAO.exist(id);
				if(deutschland == 0){
					a=true;
					
				    Responsable respo = respoDAO.getRespo(id_respo);
					String RECIPIENT = respo.getEmail();
					String USER_NAME = "Tracking.GmbH";
					 String PASSWORD = "Bachelor."; 
					
					        String from = USER_NAME;
					        String pass = PASSWORD;
					        String[] to = { RECIPIENT };
					        String subject = "Bekanntmachung . ";
					        String body = "Guten Tag, Herr. "+respo.getNom()+". Sie haben die erste Aufgabe der Datei Nummer '"+id_doc+" vom Typ '"+d_type+"' "
					        		+ "' zu genehmigen und zu vervollständigen. Letzter Termin die '"+dateFin_+"' .";
					respoDAO.sendFromGMail(from, pass, to, subject, body);
					String message = "Die erste Aufgabe der Datei "+id+" ist hinzugefügt. (Verantwortlich: "+respo.getNom()+") ";

					Calendar rightNow = Calendar.getInstance();
					int hour = rightNow.get(Calendar.HOUR_OF_DAY);
					int minute = rightNow.get(Calendar.MINUTE);
					String Uhr= "h";
					String hr = String.valueOf(hour);
					String min = String.valueOf(minute);
					if(minute<10){
						Uhr= hr+":0"+min;
					}else{
						Uhr= hr+":"+min;
					}
					Messenger msg = new Messenger(id,0,message, null,Uhr);
					messageDAO.addMsgRespo(msg);
				}
				
				Tache nouvelleTache = new Tache(Integer.parseInt(id_respo) ,id,b
							,libelle,dateDebut_,dateFin_,d_nom_cl,d_type,0,a,duree);
				tacheDAO.addTache(nouvelleTache);
				
				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respo);
					
				}
				request.setAttribute("dossier", dossiers);	
				List<Responsable> respo =  respoDAO.ListRespo();
				request.setAttribute("responsable", respo);	
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String email = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(email);
				}
				request.setAttribute("client", clyan);	
				request.setAttribute("succes", " Der Fleck "+libelle+" ist gut hinzugefügt worden.");
				Responsable respoo = respoDAO.getRespo(id_respo);
				if(deutschland != 0){
				String message = "Die Datei Nummer "+id+" hat eine neue Aufgabe. (Verantwortlich: "+respoo.getNom()+") ";

				Calendar rightNow = Calendar.getInstance();
				int hour = rightNow.get(Calendar.HOUR_OF_DAY);
				int minute = rightNow.get(Calendar.MINUTE);
				String Uhr= "h";
				String hr = String.valueOf(hour);
				String min = String.valueOf(minute);
				if(minute<10){
					Uhr= hr+":0"+min;
				}else{
					Uhr= hr+":"+min;
				}
				Messenger msg = new Messenger(id,0,message, null,Uhr);
				messageDAO.addMsgRespo(msg);
				}
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addTache.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			}	
	}
		
		if(request.getServletPath().equals("/login")) {
			String exceptionContent = "Bei der Authentifizierung ist ein Fehler aufgetreten.";
			try{
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				if(email.equals("aymen") && password.equals("000")){
					// dossiers
					List<Dossier> dossiers =  dossierDAO.ListDossier();
					for (Dossier dossier : dossiers) {
						String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
						String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
						int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
						int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
						int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
						String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
						dossier.setDateDebut(dateDebut);
						dossier.setDateFin(dateFin);
						dossier.setTotalTache(totalTache);
						dossier.setMaxTache(maxTache);
						dossier.setDureeTotal(dureeTotal);
						dossier.setRespo(respo);
						
					}
					
					request.setAttribute("dossier", dossiers);	
					
					// responsables
					List<Responsable> respo =  respoDAO.ListRespo();
					request.setAttribute("responsable", respo);
					List<Client> clyan =  clientDAO.ListClient();
					for (Client client : clyan) {
						String emaill = clientmailDAO.getMailClient(client.getId_client());
						client.setEmail(emaill);
					}
					request.setAttribute("client", clyan);	
					request.setAttribute("succes", "Willkommen Aymen!");
					request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
				}else{
					boolean status = false;
					status=respoDAO.login(email, password);
					if(status){
						session = request.getSession();
						Responsable utilisateur = respoDAO.getRespo(email, password);
						session.setAttribute("session", utilisateur);
						List<Tache> tach =  tacheDAO.ListTacheRespo(utilisateur.getId_respo());
						request.setAttribute("responsable", utilisateur);
						request.setAttribute("tache", tach);
						request.setAttribute("succes", "Willkommen "+ utilisateur.getNom() + "! Sie haben sich authentifiziert");
						if(session != null){
						Responsable utilisateurConnecte = (Responsable) request.getSession().getAttribute("session");
						int id_respoo = utilisateurConnecte.getId_respo();
						List<Tache> tachApr =  tacheDAO.ListTacheRespoApprouver(id_respoo);
						request.setAttribute("tachApr", tachApr);
						List<Tache> tachEC =  tacheDAO.ListTacheRespoEnCours(id_respoo);
						request.setAttribute("tachEC", tachEC);
						List<Tache> tachProg =  tacheDAO.ListTacheRespo(id_respoo);
						request.setAttribute("tachProg", tachProg);
						int totalAp = tacheDAO.TotalApprouve(id_respoo);
						int totalEn = tacheDAO.TotalEnCours(id_respoo);
						//---
						for (Tache tache : tachProg) {
							int totalTache = tacheDAO.TotalTacheTache(tache.getId_doc());
							int maxTache = tacheDAO.MaxTacheTache(tache.getId_doc());
							tache.setTotalTache(totalTache);
							tache.setMaxTache(maxTache);
						}
						
						request.setAttribute("totalAp", totalAp);
						request.setAttribute("totalEn", totalEn);
						//fin liste HEADER
						}
						request.getRequestDispatcher("WEB-INF/respo.jsp").forward(request, response);

					}
					else{
						
						
					
					exceptionContent = "falsches Login oder Passwort";
					throw new Exception(exceptionContent);
					}
				}
			} catch (Exception e) {
				request.setAttribute("erreur", exceptionContent);	
				request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
			
			}
		}
		
		if(request.getServletPath().equals("/editTache")) {
			try {
				int idTache=Integer.parseInt(request.getParameter("id_tache"));
				int id_respo=Integer.parseInt(request.getParameter("id_respo"));
				String libelle = request.getParameter("libelle");
				String type = request.getParameter("type"); 
				String nomCl = request.getParameter("nom_cl");
				String dateDebut_ = request.getParameter("dateDebut"); 
				String dateFin_ = request.getParameter("dateFin"); 
				
				Tache tachtacha = tacheDAO.getOneOneTache(idTache);
				int id_doc =tachtacha.getId_doc();
				boolean b = tachtacha.isValide();
				int ntec = tachtacha.getD_numTacheEnCour();
				boolean a = tachtacha.isTurn();
				int duree = tachtacha.getDuree();
				request.setAttribute("succes", "Fleck "+idTache +" ist gut verändert worden");	
				Tache nouvelleTache = new Tache(idTache,id_respo ,id_doc,b
						,libelle,dateDebut_,dateFin_,nomCl,type,ntec,a,duree);
			tacheDAO.editTache(nouvelleTache);
				
			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);	
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
		} catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/editTache.jsp").forward(request, response);
		} finally {
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}
	} 
		
		if(request.getServletPath().equals("/editClient")) {
			try {
			String emailOriginal = request.getParameter("emailOriginal");
			String email = request.getParameter("email");
			int idM=clientmailDAO.getIdCM(emailOriginal);
			clientmailDAO.editMail(idM,email); 
			String idC = request.getParameter("id_client");
			int id_client=Integer.parseInt(idC);
			String nom = request.getParameter("nom"); 
			String password = request.getParameter("password"); 
			String adresse = request.getParameter("adresse"); 
			String tel = request.getParameter("tel"); 
//			String carte = request.getParameter("carte"); 
//			String ccv = request.getParameter("ccv"); 
//			int cc=Integer.parseInt(ccv);
//			String expiry = request.getParameter("expiry"); 
//			String premium = request.getParameter("premium"); 
//			boolean b = Boolean. valueOf(premium);
			Client nouveauClient = clientDAO.getClient(idC);
			String mr=nouveauClient.getNom();
			clientDAO.editClient(id_client,nom ,password ,adresse , tel);

			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);		
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String emaill = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(emaill);
			}
			request.setAttribute("client", clyan);	
			request.setAttribute("succes", "Herr "+mr+" wurde geändert.");	
		} catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/editClient.jsp").forward(request, response);
		} finally {
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}	
			}
		
		if(request.getServletPath().equals("/editDoc")) {
			try {
			int id_doc=Integer.parseInt(request.getParameter("id_doc"));
			String nomCl = request.getParameter("nom_cl");
			String type = request.getParameter("type"); 
			String numTache = "1";
			
			Dossier nouveauDossier = new Dossier(nomCl,type,Integer.parseInt(numTache));
			nouveauDossier.setId_doc(id_doc);
			String mr=nouveauDossier.getNom_cl();
			dossierDAO.editDossier(nouveauDossier);


			List<Dossier> dossiers =  dossierDAO.ListDossier();
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("dossier", dossiers);	
			List<Responsable> respo =  respoDAO.ListRespo();
			request.setAttribute("responsable", respo);		
			List<Client> clyan =  clientDAO.ListClient();
			for (Client client : clyan) {
				String email = clientmailDAO.getMailClient(client.getId_client());
				client.setEmail(email);
			}
			request.setAttribute("client", clyan);	
			request.setAttribute("succes", "Die Datei von Herrn "+mr+" wurde bearbeitet.");	
		} catch (Exception e) {
			request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
			request.getRequestDispatcher("WEB-INF/editDoc.jsp").forward(request, response);
		} finally {
			request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
		}	
			}
		
		if(request.getServletPath().equals("/editRespo")) {
			try {
				int id_respo=Integer.parseInt(request.getParameter("id_respo"));
				String nom = request.getParameter("nom");
				String email = request.getParameter("email");
				String password = request.getParameter("password");	
				String photo = request.getParameter("photo");
				
				
				Responsable nouveauRespo = new Responsable(id_respo,nom, email,password,photo );
				respoDAO.editRespo(nouveauRespo);

				
				List<Dossier> dossiers =  dossierDAO.ListDossier();
				for (Dossier dossier : dossiers) {
					String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
					String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
					int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
					int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
					int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
					String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
					dossier.setDateDebut(dateDebut);
					dossier.setDateFin(dateFin);
					dossier.setTotalTache(totalTache);
					dossier.setMaxTache(maxTache);
					dossier.setDureeTotal(dureeTotal);
					dossier.setRespo(respo);
					
				}
				request.setAttribute("dossier", dossiers);	
				List<Responsable> respo =  respoDAO.ListRespo();
				request.setAttribute("responsable", respo);	
				List<Client> clyan =  clientDAO.ListClient();
				for (Client client : clyan) {
					String emaill = clientmailDAO.getMailClient(client.getId_client());
					client.setEmail(emaill);
				}
				request.setAttribute("client", clyan);	
				request.setAttribute("succes", "Die verantwortliche Person "+nom+" wurde geändert.");	
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addRespo.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);
			}	
	}
		
		if(request.getServletPath().equals("/contact")) {
			String RECIPIENT = request.getParameter("email");
			String USER_NAME = "Kargo.GmbH";  // GMail user name (just the part before "@gmail.com")
			 String PASSWORD = "Bachelor."; // GMail password

			        String from = USER_NAME;
			        String pass = PASSWORD;
			        String[] to = { RECIPIENT }; // list of recipient email addresses
			        String subject = "Confirmation du réception";
			        String body = "Votre Email a été bien recu. Nous allons vous contacter une fois le LIVE CHAT est disponible!";

			        respoDAO.sendFromGMail(from, pass, to, subject, body);
			        request.setAttribute("succes", "Email Envoyé avec succes!");
					request.getRequestDispatcher("WEB-INF/acceuil.jsp").forward(request, response);

		}
		
		if(request.getServletPath().equals("/registration")) {
			try{
			String email = request.getParameter( "email" );
			String nom = request.getParameter( "nom" );
			String password = request.getParameter( "password" );
			String tel = request.getParameter( "tel" );
			String id_doc = request.getParameter( "id_doc" );
			int id_clientmail=clientmailDAO.getIdCM(email);
			boolean prem = false;
			Client c = new Client(id_clientmail,nom,password,tel,prem);
			clientDAO.addClient(c);
			session = request.getSession();
			int id=clientmailDAO.getIdCM(email);
			Client client = clientDAO.getClient(String.valueOf(id), password);
			session.setAttribute("session", client);
			request.setAttribute("succes", "Sie haben sich erfolgreich registriert");
			request.setAttribute("id_doc", id_doc);
			String nom_cl = client.getNom();
			List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("client", client);
			request.setAttribute("dossier", dossiers);
			} catch (Exception e) {
				request.setAttribute("erreur", "Bitte überprüfen Sie, ob Sie die Formularfelder korrekt ausgefüllt haben! ");
				request.getRequestDispatcher("WEB-INF/addRespo.jsp").forward(request, response);
			} finally {
				request.getRequestDispatcher("WEB-INF/acceuilClient.jsp").forward(request, response);
			}	
		}
		
		if(request.getServletPath().equals("/premium")) {
			try{
				String id_client = request.getParameter( "id_client" );
			String adresse = request.getParameter( "adresse" );
			String zip = request.getParameter( "zip" );
			String numcarte = request.getParameter( "numcarte" );
			String ccv = request.getParameter( "ccv" );
			String expiry = request.getParameter( "expiry" );
			String ad=adresse+" "+zip;
			boolean prem=true;
			int id=Integer.parseInt(id_client);
			int cc=Integer.parseInt(ccv);
			clientDAO.updateClient(id,ad,numcarte,cc,expiry,prem);
			Client clyan = clientDAO.getClient(id_client);
			String nom_cl = clyan.getNom();
			List<Dossier> dossiers =  dossierDAO.ListDossierClient( nom_cl);
			for (Dossier dossier : dossiers) {
				String dateDebut = tacheDAO.getDatePremiereTache(dossier.getId_doc());
				String dateFin = tacheDAO.getDateDerniereTache(dossier.getId_doc());
				int totalTache = tacheDAO.TotalTache(dossier.getId_doc());
				int maxTache = tacheDAO.MaxTache(dossier.getId_doc());
				int dureeTotal = tacheDAO.DureeTotal(dossier.getId_doc());
				String respo = tacheDAO.RespoAdmin(dossier.getId_doc());
				dossier.setDateDebut(dateDebut);
				dossier.setDateFin(dateFin);
				dossier.setTotalTache(totalTache);
				dossier.setMaxTache(maxTache);
				dossier.setDureeTotal(dureeTotal);
				dossier.setRespo(respo);
				
			}
			request.setAttribute("client", clyan);
			request.setAttribute("dossier", dossiers);
			request.setAttribute("succes", "Sie sind ein Premium-Kunde!");
			request.setAttribute("id_client", id_client);
			
			request.getRequestDispatcher("WEB-INF/loginClient.jsp").forward(request, response);
			} catch (Exception e) {
				request.getRequestDispatcher("WEB-INF/premium.jsp").forward(request, response);
			}	
		}
		
		
	} catch (Exception e) {
		request.setAttribute("erreur", "Es wurde ein Fehler gemacht.");
		System.out.println("error do post: "+e.getMessage());

		request.getRequestDispatcher("WEB-INF/404.jsp").forward(request, response);
	}
//---------------------	
	}

}


