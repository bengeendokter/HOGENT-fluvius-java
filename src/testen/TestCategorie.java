package testen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.DTOCategorie;
import domein.DomeinController;
import domein.Fluvius;
import domein.Gebruiker;
import domein.SDGCategorie;
import domein.SdGoal;
import repository.CategorieDao;
import repository.CategorieDaoJpa;
import repository.MVODatasourceDao;
import repository.MVODatasourceDaoJpa;
import repository.MVODoelstellingDao;
import repository.MVODoelstellingDaoJpa;
import repository.SdGoalDao;
import repository.SdGoalDaoJpa;

@ExtendWith(MockitoExtension.class)
public class TestCategorie {
	
	@Mock
    private CategorieDao categorieRepo;
	@Mock
    private MVODoelstellingDao doelstellingRepo;
	@Mock
    private SdGoalDao goalRepo;
	@Mock
    private MVODatasourceDao dataRepo;
	
	@InjectMocks
	private static Fluvius fluvius;
	
	
	
	

}
