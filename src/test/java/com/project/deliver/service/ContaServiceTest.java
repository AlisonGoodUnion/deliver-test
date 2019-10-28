package com.project.deliver.service;

import com.project.deliver.domain.Conta;
import com.project.deliver.exception.BusinessExceltion;
import com.project.deliver.fixture.ContaFixture;
import com.project.deliver.repository.ContaRepository;
import com.project.deliver.validator.ContaValidator;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ContaServiceTest {

    @InjectMocks
    private ContaService contaService;

    @Mock
    private ContaRepository contaRepository;
    @Mock
    private ContaValidator contaValidator;
    @Mock
    private AtrasoService atrasoService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //@Test
    public void given_IncluirConta_when_vencimentoNull_thenBussinesException() {
        //TODO AJUSTAR TESTES DEVE RETORNAR EXCEPTION.
        Conta contaVencimentoNull = ContaFixture.get().random().vencimento(null).build();
        thrown.expectMessage("[Conta]: Vencimento inválido");
        thrown.expect(BusinessExceltion.class);

        contaService.incluir(contaVencimentoNull);

        verify(contaValidator, times(1)).accept(contaVencimentoNull);
        verifyNoInteractions(atrasoService);
        verifyNoInteractions(contaRepository);
    }

}