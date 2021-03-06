CREATE OR REPLACE FORCE EDITIONABLE VIEW PRO_V_PROSPETTO_PROVINCIA (ID_PROSPETTO_PROV, ID_PROSPETTO,
ID_T_PROVINCIA, DS_TARGA, DS_PRO_T_PROVINCIA, N_TOTALE_LAVORAT_DIPENDENTI, N_PART_TIME, N_INTERMITTENTI,
N_DISABILI_IN_FORZA, N_CENTRAL_TELEFO_NONVEDENTI, N_TERARIAB_MASSOFIS_NONVED, N_SOMMINISTRATI_FT,
N_CONVENZIONI_12BIS_14_FT, N_TELELAVORO_FT, N_LAV_APPARTART_CATEGORIA, N_CATE_PROT_FORZA,
N_CATE_PROT_FORZA_A_17_01_2000, N_LAV_APPARTART_CATEG_ART_18, CONVENZIONE, SOSP_IN_CORSO,
N_ASSUNZIONI_EFF_DOPO_TRASF, N_POSTI_DISP, FLG_CONFERMATO_Q2, N_LAVORATORI_ESONERO,N_TELELAVORO_PT) AS
SELECT prpp.ID_PROSPETTO_PROV, prpp.ID_PROSPETTO, prpp.id_t_provincia, ptp.DS_TARGA, ptp.DS_PRO_T_PROVINCIA, pddp.N_TOTALE_LAVORAT_DIPENDENTI,
(
  SELECT NVL(sum(N_PART_TIME),0)
    FROM PRO_D_PART_TIME pdpt2
   WHERE ID_PROSPETTO_PROV = prpp.ID_PROSPETTO_PROV AND ID_TIPO_RIPROP_PT <> 4
) N_PART_TIME, 
(
  SELECT NVL(sum(pdpi.N_INTERMITTENTI),0)
    FROM PRO_D_PROV_INTERMITTENTI pdpi
   WHERE ID_PROSPETTO_PROV = prpp.ID_PROSPETTO_PROV
) N_INTERMITTENTI, 
pddp.N_DISABILI_IN_FORZA, pddp.N_CENTRAL_TELEFO_NONVEDENTI, pddp.N_TERARIAB_MASSOFIS_NONVED, pddp.N_SOMMINISTRATI_FT, pddp.N_CONVENZIONI_12BIS_14_FT, pddp.N_TELELAVORO_FT,
(
  SELECT NVL(sum(pdce.N_LAV_APPARTART_CATEGORIA),0)
    FROM PRO_D_CATEGORIE_ESCLUSE pdce, PRO_T_CATEGORIA_ESCLUSE ptce
   WHERE ptce.ID_T_CATEGORIA_ESCLUSE = pdce.ID_T_CATEGORIE_ESCLUSE AND ptce.AMBITO_CATEGORIA IN('E','D') AND pdce.ID_PROSPETTO_PROV = prpp.ID_PROSPETTO_PROV
) N_LAV_APPARTART_CATEGORIA,
pddp.N_CATE_PROT_FORZA, pddp.N_CATE_PROT_FORZA_A_17_01_2000,
(
  SELECT NVL(sum(pdce.N_LAV_APPARTART_CATEGORIA),0)
    FROM PRO_D_CATEGORIE_ESCLUSE pdce, PRO_T_CATEGORIA_ESCLUSE ptce
   WHERE ptce.ID_T_CATEGORIA_ESCLUSE = pdce.ID_T_CATEGORIE_ESCLUSE AND ptce.AMBITO_CATEGORIA IN('E','C') AND pdce.ID_PROSPETTO_PROV = prpp.ID_PROSPETTO_PROV
) N_LAV_APPARTART_CATEG_ART_18,
(
  CASE 
    WHEN EXISTS (SELECT 1
                   FROM PRO_D_PROV_CONVENZIONE pdpc
                  WHERE pdpc.ID_PROSPETTO_PROV = prpp.ID_PROSPETTO_PROV )
      THEN 'S' 
    ELSE 'N' 
  END 
) CONVENZIONE,
(
  CASE 
    WHEN EXISTS (SELECT 1
                   FROM PRO_D_PROV_SOSPENSIONE pdps, PRO_T_STATO_CONCESSIONE ptsc
                  WHERE pdps.ID_PROSPETTO_PROV = prpp.ID_PROSPETTO_PROV AND pdps.ID_T_STATO_CONCESSIONE = ptsc.ID_T_STATO_CONCESSIONE AND ptsc.COD_STATO_CONCESSIONE = 'E')
      THEN 'S' 
    ELSE 'N' 
  END
) SOSP_IN_CORSO, 
pdpg.N_ASSUNZIONI_EFF_DOPO_TRASF,
(
  SELECT NVL(sum(pdpld.N_POSTI),0)
    FROM PRO_D_POSTI_LAVORO_DISP pdpld
   WHERE pdpld.ID_PROSPETTO_PROV = prpp.ID_PROSPETTO_PROV 
) N_POSTI_DISP, 
prpp.FLG_CONFERMATO_Q2,
pdpe.N_LAVORATORI_ESONERO, 
(
  SELECT NVL(sum(N_PART_TIME),0)
    FROM PRO_D_PART_TIME pdpt2
   WHERE ID_PROSPETTO_PROV = prpp.ID_PROSPETTO_PROV AND ID_TIPO_RIPROP_PT = 4
) N_TELELAVORO_PT
  FROM PRO_R_PROSPETTO_PROVINCIA prpp, 
       PRO_T_PROVINCIA ptp, 
       PRO_D_DATI_PROVINCIALI pddp, 
       PRO_D_PROV_ESONERO pdpe, 
       PRO_D_PROV_GRADUALITA pdpg
 WHERE prpp.ID_T_PROVINCIA = ptp.ID_T_PROVINCIA
   AND pddp.ID_PROSPETTO_PROV (+)= prpp.ID_PROSPETTO_PROV
   AND pdpe.ID_PROSPETTO_PROV (+)= prpp.ID_PROSPETTO_PROV
   AND pdpg.ID_PROSPETTO_PROV (+)= prpp.ID_PROSPETTO_PROV
;