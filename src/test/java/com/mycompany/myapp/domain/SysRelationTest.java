package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SysRelationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysRelation.class);
        SysRelation sysRelation1 = new SysRelation();
        sysRelation1.setId(1L);
        SysRelation sysRelation2 = new SysRelation();
        sysRelation2.setId(sysRelation1.getId());
        assertThat(sysRelation1).isEqualTo(sysRelation2);
        sysRelation2.setId(2L);
        assertThat(sysRelation1).isNotEqualTo(sysRelation2);
        sysRelation1.setId(null);
        assertThat(sysRelation1).isNotEqualTo(sysRelation2);
    }
}
