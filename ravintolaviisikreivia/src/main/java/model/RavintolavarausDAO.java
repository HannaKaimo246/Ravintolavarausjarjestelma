package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import javafx.scene.layout.AnchorPane;


/**
 * Luodaan tietokantayhteys
 * @author R27
 * @since 2021/12/17
 *
 */
public class RavintolavarausDAO implements IRavintolavarausDAO {
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	
	
	SessionFactory istuntotehdas = null;

	public RavintolavarausDAO() {

		try {
			
			System.out.println("Yhdistetään tietokantaan...");
		
			istuntotehdas = new Configuration().configure().buildSessionFactory();
			
			System.out.println("Tietokannan luominen onnistui!");
			
		} catch (Exception e) {
			System.err.println("Tietokannan luominen ei onnistunut.");
			e.printStackTrace();
			
			System.exit(-1);
		}

	}
	/**
	 * tietokantayhteyden katkaiseminen
	 */
	public void finalize() {

		try {
			if (istuntotehdas != null)
				istuntotehdas.close();

		} catch (Exception e) {
			System.err.println("Tietokannan sulkeminen epäonnistui: " + e.getMessage());
		}

	}
	
	/**
	 * Luodaan palaute
	 */
	@Override
	public boolean luoPalaute(Palaute palaute) {

		System.out.print("Luodaan palaute...");
		
		Transaction transaktio = null;
		boolean luonti = false;
		
		try (Session istunto = istuntotehdas.openSession()) {

			if ((palaute.getAihe() != null && !palaute.getAihe().isEmpty() || palaute.getTyontekija() != null) && palaute.getViesti() != null && !palaute.getViesti().isEmpty()) {
				
				transaktio = istunto.beginTransaction();

				istunto.saveOrUpdate(palaute);

				istunto.getTransaction().commit();

				luonti = true;

			} else {
				luonti = false;
			}

		} catch (Exception e) {
			System.out.println("Palautetta ei voitu luoda.");
			e.printStackTrace();
			if (transaktio != null)
				throw e;

			luonti = false;
		}

		return luonti;
	}

	/**
	 * Asiakkaan rekisteröityminen
	 */
	public boolean luoAsiakas(Asiakas asiakas) {

		Transaction transaktio = null;
		boolean luonti = false;
		
		try (Session istunto = istuntotehdas.openSession()) {

			if (asiakas.getEtuNimi() != null && !asiakas.getEtuNimi().isEmpty() && asiakas.getSukuNimi() != null && ! asiakas.getSukuNimi().isEmpty() && asiakas.getPuhelinNumero() != null && ! asiakas.getPuhelinNumero().isEmpty() && asiakas.getSahkopostiOsoite() != null && ! asiakas.getSahkopostiOsoite().isEmpty() &&
					asiakas.getSalasana() != null && ! asiakas.getSalasana().isEmpty() && asiakas.getAsiakasNimimerkki() != null && ! asiakas.getAsiakasNimimerkki().isEmpty()) {
				
				transaktio = istunto.beginTransaction();

				istunto.saveOrUpdate(asiakas);

				istunto.getTransaction().commit();

				luonti = true;

			} else {
				luonti = false;
			}

		} catch (Exception e) {
			System.out.println("Asiakas ei voitu luoda.");
			e.printStackTrace();
			if (transaktio != null)
				throw e;

			luonti = false;
		}

		return luonti;
	}

	/**
	 * Haetaan kaikki palautteet työntekijälle
	 */
	@Override
	public List<Palaute> haePalautteetTyontekija() {
		Transaction transaktio = null;

		ArrayList<Palaute> list = new ArrayList<Palaute>();
		ArrayList<String> viiteavaimet = new ArrayList<String>();
		try (Session istunto = istuntotehdas.openSession()) {

			istunto.beginTransaction();
			List<Palaute> result = istunto.createQuery("from Palaute").list();
		
			for (Palaute p : (List<Palaute>) result) {

				Palaute palaute = new Palaute();

				palaute.setAihe(p.getAihe());

				palaute.setTunnus(p.getTunnus());
				
				palaute.setViesti(p.getViesti());
				
				palaute.setAika(p.getAika());
				
				palaute.setVastaus(p.getVastaus());
				
				palaute.setAsiakas(p.getAsiakas());
				
				palaute.setTyontekija(p.getTyontekija());
				
				if (p.getVastaus() != null) {
					
					String value = String.valueOf(p.getVastaus().palauteID);
					
					viiteavaimet.add(value);
					
				}
				list.add(palaute);
			}
			istunto.getTransaction().commit();

		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
		}
		
		Iterator<Palaute> itr = list.iterator();            
		while(itr.hasNext()){
		    Palaute palaute = itr.next();
		 
			String index = String.valueOf(palaute.getTunnus());
		    
			if (viiteavaimet.contains(index)) {
				 
				itr.remove();
		
			}
		}

		return list;
		
	}
	
	/**
	 * Luodaan varaus
	 */
	@Override
	public boolean luoVaraus(Varaus varaus) {

		Transaction transaktio = null;
		boolean luonti = false;
		
		try (Session istunto = istuntotehdas.openSession()) {

			if (varaus.getPaiva() != null && varaus.getAlkamisAjankohta() != null && varaus.getPaattymisAjankohta() != null && varaus.getHenkiloMaara() != 0) {
				
				transaktio = istunto.beginTransaction();

				istunto.saveOrUpdate(varaus);

				istunto.getTransaction().commit();

				luonti = true;

			} else {
				luonti = false;
			}

		} catch (Exception e) {
			System.out.println("Varausta ei voitu luoda.");
			e.printStackTrace();
			if (transaktio != null)
				throw e;

			luonti = false;
		}

		return luonti;
	}
	/**
	 * Poistetaan varaus
	 */
	public boolean deleteVaraus(Varaus varaus) {
		Transaction transaktio = null;

		boolean poisto = false;
		
		try (Session istunto = istuntotehdas.openSession()) {

			if (varaus.getVarausID() != 0) {
				
				transaktio = istunto.beginTransaction();

				istunto.delete(varaus);

				istunto.getTransaction().commit();

				poisto = true;

			} else {
				poisto = false;
			}

		} catch (Exception e) {
			System.out.println("Varausta ei voitu luoda.");
			e.printStackTrace();
			if (transaktio != null)
				throw e;

			poisto = false;
		}

		return poisto;
	};

	
	/**
	 * Luodaan työntekijä
	 */
		public boolean luoTyontekija(Tyontekija tyontekija) {
			
				Transaction transaktio = null;
				
				String rooli = tyontekija.getRooli();
				
				String salasana = tyontekija.getSalasana();

				String nimimerkki = tyontekija.getTyontekijaNimiMerkki();

				boolean luonti = false;
				
				try (Session istunto = istuntotehdas.openSession()) {

					if (rooli != null && !rooli.isEmpty() && salasana != null && !salasana.isEmpty() && nimimerkki != null && !nimimerkki.isEmpty()) {
						
						transaktio = istunto.beginTransaction();

						istunto.saveOrUpdate(tyontekija);

						istunto.getTransaction().commit();

						luonti = true;

					} else {
						luonti = false;
					}

				} catch (Exception e) {
					System.out.println("Tyontekija ei voitu luoda.");
					e.printStackTrace();
					if (transaktio != null)
						throw e;

					luonti = false;
				}

				return luonti;
			}
		
		public boolean deleteTyontekija(Tyontekija tyontekija) {
			Transaction transaktio = null;

			boolean poisto = false;
			
			try (Session istunto = istuntotehdas.openSession()) {

				if (tyontekija.getTyontekijaID() != 0) {
					
					transaktio = istunto.beginTransaction();

					istunto.delete(tyontekija);

					istunto.getTransaction().commit();

					poisto = true;

				} else {
					poisto = false;
				}

			} catch (Exception e) {
				System.out.println("Työntekijää ei voitu poistaa.");
				e.printStackTrace();
				if (transaktio != null)
					throw e;

				poisto = false;
			}

			return poisto;
		};
		
		
		/**
		 * Listään ruoka ruokalistään
		 */
			public boolean luoRuoka(Ruoka ruoka) {
				
					Transaction transaktio = null;
					
					String nimi = ruoka.getNimi();
					
					String kuvaus = ruoka.getKuvaus();

					double hinta = ruoka.getHinta();
					
					String kategoria = ruoka.getKategoria();

					boolean luonti = false;
					
					try (Session istunto = istuntotehdas.openSession()) {

						if (nimi != null && !nimi.isEmpty() && kuvaus != null && !kuvaus.isEmpty() && kategoria != null && !kategoria.isEmpty()) {
							
							transaktio = istunto.beginTransaction();

							istunto.saveOrUpdate(ruoka);

							istunto.getTransaction().commit();

							luonti = true;

						} else {
							luonti = false;
						}

					} catch (Exception e) {
						System.out.println("Ruoka ei voitu luoda.");
						e.printStackTrace();
						if (transaktio != null)
							throw e;

						luonti = false;
					}

					return luonti;
				}
			
			
			/**
			 * Ruoan poistaminen ruokalistasta
			 */
			public boolean deleteAteria(Ruoka ruoka) {
				Transaction transaktio = null;

				boolean poisto = false;
				
				try (Session istunto = istuntotehdas.openSession()) {

					if (ruoka.getRuokaID() >= 0) {
						
						transaktio = istunto.beginTransaction();

						istunto.delete(ruoka);

						istunto.getTransaction().commit();

						poisto = true;

					} else {
						poisto = false;
					}

				} catch (Exception e) {
					System.out.println("Varausta ei voitu luoda.");
					e.printStackTrace();
					if (transaktio != null)
						throw e;

					poisto = false;
				}

				return poisto;
			};
		
	/**
	 * Haetaan ruoan kategoriat tietokannasta
	 */
	@Override
	public List<Ruoka> readRuokaKategoria(Ruoka ruoka, String kategoria) {
		Transaction transaktio = null;
		
		ArrayList<Ruoka> list = new ArrayList<Ruoka>();
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			List<Ruoka> result = istunto.createQuery("from Ruoka WHERE Kategoria = '" + kategoria + "' ").getResultList();
			
			for (Ruoka r : (List<Ruoka>) result) {

				ruoka = new Ruoka();
				
				ruoka.setRuokaID(r.getRuokaID());
				
				ruoka.setNimi(r.getNimi());
				
				ruoka.setHinta(r.getHinta());
				
				ruoka.setKuvaus(r.getKuvaus());
				
				ruoka.setKategoria(r.getKategoria());
								
				list.add(ruoka);

			}
			
		}catch(Exception e) {
			System.err.println("Virhe!");
		}

		return list;
	}
	
	
	/**
	 * Muokataan aterian tiedot
	 */
	@Override
	public boolean updateMeal(Ruoka ruoka) {
		try (Session istunto = istuntotehdas.openSession()) {

			int id = ruoka.getRuokaID();
			String nimi = ruoka.getNimi();
			String kuvaus = ruoka.getKuvaus();
			String kategoria = ruoka.getKategoria();
			double hinta = ruoka.getHinta();

			istunto.beginTransaction();

			ruoka = (Ruoka)istunto.get(Ruoka.class, ruoka.getRuokaID() );

			if (ruoka != null) {
				ruoka.setNimi(nimi);
				ruoka.setKuvaus(kuvaus);
				ruoka.setKategoria(kategoria);
				ruoka.setHinta(hinta);
			} else {
				System.out.println("P�ivitys ep�onnistui.");
				return false;
			}

			istunto.getTransaction().commit();

			return true;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return false;
		}
	}
	
	
	/**
	 * Muokkataan työntekijän tiedot
	 */
	@Override
	public boolean updateEmployee(Tyontekija tyontekija) {
		try (Session istunto = istuntotehdas.openSession()) {

			int id = tyontekija.getTyontekijaID();
			String nimi = tyontekija.getTyontekijaNimiMerkki();
			String rooli = tyontekija.getRooli();
			String salasana = tyontekija.getSalasana();
			

			istunto.beginTransaction();

			tyontekija = (Tyontekija)istunto.get(Tyontekija.class, tyontekija.getTyontekijaID());

			if (tyontekija != null) {
				tyontekija.setTyontekijaNimiMerkki(nimi);
				tyontekija.setRooli(rooli);
				tyontekija.setSalasana(salasana);
			} else {
				System.out.println("P�ivitys ep�onnistui.");
				return false;
			}

			istunto.getTransaction().commit();

			return true;

		} catch (Exception e) {
			System.err.println("Virhe!");
			return false;
		}
	}
	
	/**
	 * Työntekijän tietojen poistaaminen ruokalistasta
	 */
	public boolean deleteEmployee(Tyontekija tyontekija) {
		Transaction transaktio = null;

		boolean poisto = false;
		
		try (Session istunto = istuntotehdas.openSession()) {

			if (tyontekija.getTyontekijaID() >= 0) {
				
				transaktio = istunto.beginTransaction();

				istunto.delete(tyontekija);

				istunto.getTransaction().commit();

				poisto = true;

			} else {
				poisto = false;
			}

		} catch (Exception e) {
			System.out.println("Varausta ei voitu luoda.");
			e.printStackTrace();
			if (transaktio != null)
				throw e;

			poisto = false;
		}

		return poisto;
	};

	
	
	
	/**
	 * Haetaan työntekijät tietokannasta
	 */
	@Override
	public List<Tyontekija> haeTyontekijat(Tyontekija tyontekija) {
		Transaction transaktio = null;
		
		ArrayList<Tyontekija> list = new ArrayList<Tyontekija>();
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			List<Tyontekija> result = istunto.createQuery("from Tyontekija").getResultList();
		
			for (Tyontekija t : (List<Tyontekija>) result) {

				tyontekija = new Tyontekija();
				
				tyontekija.setTyontekijaID(t.getTyontekijaID());
				
				tyontekija.setTyontekijaNimiMerkki(t.getTyontekijaNimiMerkki());
				
				tyontekija.setRooli(t.getRooli());
								
				list.add(tyontekija);

			}
			
		}catch(Exception e) {
			System.err.println("Virhe!");
		}

		
		
		return list;
	}

	/**
	 * Tarkistetaan onko käyttäjä asiakas
	 */
	@Override
	public Asiakas onkoAsiakas(String asiakasNimimerkki, String Asiakas_salasana) {
		
		Transaction transaktio = null;
		
		Asiakas asiakas = null;

		try (Session istunto = istuntotehdas.openSession()) {

			istunto.beginTransaction();
         
			asiakas = (Asiakas) istunto.createQuery("FROM Asiakas U WHERE U.asiakasNimimerkki = :asiakasNimimerkki").setParameter("asiakasNimimerkki", asiakasNimimerkki)
                    .uniqueResult();

                if (asiakas != null && asiakas.getSalasana().equals(Asiakas_salasana)) {
                	System.out.println("Asiakas onnistui");
                
                } else {
                	asiakas = null;
                	System.out.println("Asiakas ei onnistunut");
                }
       
            istunto.getTransaction().commit();

		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
		}
		
		return asiakas;
	}

	/**
	 * Tarkistetaan onko käyttäjä työntekijä
	 */
	@Override
	public Tyontekija onkoTyontekija(String tyontekijaNimimerkki, String salasana) {
		
		Transaction transaktio = null;
		
		Tyontekija tyontekija = null;

		try (Session istunto = istuntotehdas.openSession()) {
		 
			istunto.beginTransaction();
         
			tyontekija = (Tyontekija) istunto.createQuery("FROM Tyontekija U WHERE U.tyontekijaNimimerkki = :tyontekijaNimimerkki").setParameter("tyontekijaNimimerkki", tyontekijaNimimerkki)
                    .uniqueResult();
	
                if (tyontekija != null && tyontekija.getSalasana().equals(salasana)) {
                	System.out.println("Työntekijä onnistui");
                
                } else {
                	tyontekija = null;
                	System.out.println("Työntekijä ei onnistunut");
                }
       
            istunto.getTransaction().commit();

		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
		}
		
		return tyontekija;

	}   
	
	/**
	 * Haetaan asiakkaan palautteet
	 */
	@Override
	public List<Palaute> haePalautteetAsiakas(int asiakasID) {
		Transaction transaktio = null;

		ArrayList<Palaute> list = new ArrayList<Palaute>();
		ArrayList<String> viiteavaimet = new ArrayList<String>();

		try (Session istunto = istuntotehdas.openSession()) {
		
			istunto.beginTransaction();
			
			String hql = "FROM Palaute WHERE asiakasID= :arvo";
			
			List<Palaute> lista = istunto.createQuery(hql).setParameter("arvo", asiakasID).getResultList();
		
			for (Palaute p : (List<Palaute>) lista) {
				
				Palaute palaute = new Palaute();

				palaute.setAihe(p.getAihe());

				palaute.setTunnus(p.getTunnus());
				
				palaute.setViesti(p.getViesti());
				
				palaute.setAika(p.getAika());
				
				palaute.setVastaus(p.getVastaus());
				
				palaute.setAsiakas(p.getAsiakas());
				
				palaute.setTyontekija(p.getTyontekija());
				
				if (p.getVastaus() != null) {
					
					String value = String.valueOf(p.getVastaus().palauteID);
					
					viiteavaimet.add(value);
					
				}
				
				list.add(palaute);
			}
			
			istunto.getTransaction().commit();

		} catch (Exception e) {
			if (transaktio != null)
				transaktio.rollback();
		}
		
		Iterator<Palaute> itr = list.iterator();            
		while(itr.hasNext()){
		    Palaute palaute = itr.next();
		 
			String index = String.valueOf(palaute.getTunnus());
		    
			if (viiteavaimet.contains(index)) {
				 
				itr.remove();
		
			}
		}
		
		return list;
		
	}

	/**
	 * Luetaan varauksia
	 */
	
	
	@Override
    public List<Varaus> readVaraukset(Varaus varaus) {
    Transaction transaktio = null;

        ArrayList<Varaus> list = new ArrayList<Varaus>();
        try (Session istunto = istuntotehdas.openSession()) {
            transaktio = istunto.beginTransaction();
            List<Varaus> result = istunto.createQuery("from Varaus").getResultList();

            for (Varaus x : (List<Varaus>) result) {

                varaus = new Varaus();

                varaus.setVarausID(x.getVarausID());

                varaus.setPaiva(x.getPaiva());

                varaus.setAlkamisAjankohta(x.getAlkamisAjankohta());

                varaus.setPaattymisAjankohta(x.getPaattymisAjankohta());

                varaus.setHenkiloMaara(x.getHenkiloMaara());

                varaus.setLisaTiedot(x.getLisaTiedot());

                varaus.setAsiakasID(x.getAsiakasID());

                list.add(varaus);
                
            

            }

        }catch(Exception e) {
            System.err.println("Virhe!");
        }

        return list;

    }

	/**
	 * Haetaan tietyn asiakkaan varaus
	 */
		@Override
		public List<Varaus> readAsiakkaanVaraus(Asiakas asiakas, Varaus varaus) {
		Transaction transaktio = null;
			
			ArrayList<Varaus> list = new ArrayList<Varaus>();
			try (Session istunto = istuntotehdas.openSession()) {
				transaktio = istunto.beginTransaction();
				
				String hql = "FROM Varaus WHERE asiakasID= :arvo";
				
				List<Varaus> result = istunto.createQuery(hql).setParameter("arvo", asiakas.getAsiakasID()).getResultList();
				
				for (Varaus x : (List<Varaus>) result) {

					varaus = new Varaus();
					
					varaus.setVarausID(x.getVarausID());
					
					varaus.setPaiva(x.getPaiva());
					
					varaus.setAlkamisAjankohta(x.getAlkamisAjankohta());
					
					varaus.setPaattymisAjankohta(x.getPaattymisAjankohta());
					
					varaus.setHenkiloMaara(x.getHenkiloMaara());
					
					varaus.setLisaTiedot(x.getLisaTiedot());
					
					varaus.setAsiakasID(x.getAsiakasID());
					
					list.add(varaus);

				}
				
			}catch(Exception e) {
				System.err.println("Virhe!");
			}

			return list;
		
		}
	
	/**
	 * Haetaan asiakas tietokannasta ID:n perusteella
	 */
	@Override
	public Asiakas haeAsiakas(int asiakasID) {
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Asiakas asiakas = new Asiakas(); // Luo olio - load tuo tiedot aina olemassaolevaan olioon
		istunto.load(asiakas, asiakasID); // Jos riviä ei ole DB:ssä, heittää ObjectNotFoundException
		System.out.println(asiakas.asiakasNimimerkki);
		istunto.getTransaction().commit();
		istunto.close();
		return asiakas;
	}
	
	// uusia metodeja...
	
	
	/**
	 * luodaan poyta
	 */
	@Override
	public boolean luoPoyta(Poyta poyta) {

		System.out.println("Luodaan poyta...");
		
		Transaction transaktio = null;

		byte[] kuva = poyta.getKuva();
			
		int maksimipaikat = poyta.getMaksimipaikat();
		
		String poydantyyppi = poyta.getPoydanTyyppi();
	
		boolean luonti = false;
		
		try (Session istunto = istuntotehdas.openSession()) {

			if (kuva != null && poydantyyppi != null && maksimipaikat != 0) {
				
				transaktio = istunto.beginTransaction();

				istunto.saveOrUpdate(poyta);

				istunto.getTransaction().commit();

				luonti = true;

			} else {
				luonti = false;
			}

		} catch (Exception e) {
			System.out.println("Poytaa ei voitu luoda.");
			e.printStackTrace();
			if (transaktio != null)
				throw e;

			luonti = false;
		}

		return luonti;
	}

	
	/**
	 * luetaan poytia
	 */
	@Override
	public List<Poyta> getPoytaTyypit() {
	Transaction transaktio = null;
		
		ArrayList<Poyta> list = new ArrayList<Poyta>();
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			List<Poyta> result = istunto.createQuery("from Poyta").getResultList();
		
			for (Poyta x : (List<Poyta>) result) {

				Poyta poyta = new Poyta();
				
				poyta.setPoytaID(x.getPoytaID());
				
				poyta.setkuva(x.getKuva());
				
				poyta.setPoydanTyyppi(x.getPoydanTyyppi());
				
				poyta.setMaksimipaikat(x.getMaksimipaikat());
				
				list.add(poyta);

			}
			
		}catch(Exception e) {
			System.err.println("Virhe!");
		}

		return list;
	
	}
	
	/**
	 * luetaan koordinaatit poytalistalle
	 */
	@Override
	public List<Koordinaatit> getPoytaLista() {
	Transaction transaktio = null;
		
		ArrayList<Koordinaatit> list = new ArrayList<Koordinaatit>();
		try (Session istunto = istuntotehdas.openSession()) {
			transaktio = istunto.beginTransaction();
			List<Koordinaatit> result = istunto.createQuery("from Koordinaatit").getResultList();
		
			for (Koordinaatit x : (List<Koordinaatit>) result) {

				Koordinaatit koordinaatit = new Koordinaatit();
				
				koordinaatit.setKoordinaattiTunnus(x.getKoordinaattiTunnus());
				
				koordinaatit.setKoordinaattiY(x.getKoordinaattiY());
				
				koordinaatit.setKoordinaattiX(x.getKoordinaattiX());
				
				koordinaatit.setKulma(x.getKulma());
				
				koordinaatit.setKoko(x.getKoko());
				
				koordinaatit.setPoyta(x.getPoyta());
			
				list.add(koordinaatit);

				
				
				
			}
			
		}catch(Exception e) {
			System.err.println("Virhe!");
		}

		return list;
	
	}
	
	/**
	 * luodaan poytalista kantaan
	 */
	@Override
	public List<Varaus> julkaise(List<Koordinaatit> poytalista) {

		Transaction transaktio = null;
		
		ArrayList<Koordinaatit> poistettava = new ArrayList<Koordinaatit>();
		
		ArrayList<Varaus> tulosLista = new ArrayList<Varaus>();
	
		try (Session istunto = istuntotehdas.openSession()) {

			if (poytalista !=null && ! poytalista.isEmpty()) {
		
				transaktio = istunto.beginTransaction();
				
				List<Varaus> result = istunto.createQuery("from Varaus").getResultList();
				
				for(Varaus x : (List<Varaus>) result) {
				
					int tunnus = x.getKoordinaatit().getKoordinaattiTunnus();
					
					for (Koordinaatit poyta : poytalista) {
						
						if (poyta.getKoordinaattiTunnus() == tunnus && poyta.getKoko() == -1) {
							
							poistettava.add(poyta);
							tulosLista.add(x);
							
						}
						
					}
					
					
				}
			
				istunto.getTransaction().commit();

			}
			
		} catch (Exception e) {
			System.out.println("Poytalistaa ei voitu luoda.");
			e.printStackTrace();
			if (transaktio != null)
				throw e;

			
		}
		
		try (Session istunto = istuntotehdas.openSession()) {
			
			transaktio = istunto.beginTransaction();
			
			for(Koordinaatit poyta : poytalista) {
				
				if (poyta.getKoko() != -1) {
					istunto.saveOrUpdate(poyta);
				} else if (!poistettava.contains(poyta)) {
					istunto.remove(poyta);
				}
				
				
			}
			
			istunto.getTransaction().commit();
		}
	

		return tulosLista;
	}

	/**
	 * poistetaan varattu varaus
	 */
	@Override
	public boolean poistaVarattuVaraus(List<Varaus> varauslista) {

		Transaction transaktio = null;

		boolean luonti = false;
		
		try (Session istunto = istuntotehdas.openSession()) {

				transaktio = istunto.beginTransaction();
				
				
				for(Varaus varaus : varauslista) {
					
					Koordinaatit arvo = varaus.getKoordinaatit();
					
					istunto.delete(arvo);
					
				}
				

				Query q = istunto.createQuery("delete Varaus where varausID =:ID");
			
				for(Varaus varaus : varauslista) {
					
					q.setParameter("ID", varaus.getVarausID());
					q.executeUpdate();
					
				}
				
				
				istunto.getTransaction().commit();

				luonti = true;

			

		} catch (Exception e) {
			System.out.println("varauksia ei voitu poistaa.");
			e.printStackTrace();
			if (transaktio != null)
				throw e;

			luonti = false;
		}

		return luonti;
	}
	
	
}
