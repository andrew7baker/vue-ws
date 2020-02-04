package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SysRelationTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRelationType.class);
        SysRelationType sysRelationType1 = new SysRelationType();
        sysRelationType1.setId(1L);
        SysRelationType sysRelationType2 = new SysRelationType();
        sysRelationType2.setId(sysRelationType1.getId());
        assertThat(sysRelationType1).isEqualTo(sysRelationType2);
        sysRelationType2.setId(2L);
        assertThat(sysRelationType1).isNotEqualTo(sysRelationType2);
        sysRelationType1.setId(null);
        assertThat(sysRelationType1).isNotEqualTo(sysRelationType2);
    }
}
