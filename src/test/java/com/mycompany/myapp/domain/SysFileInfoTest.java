package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SysFileInfoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysFileInfo.class);
        SysFileInfo sysFileInfo1 = new SysFileInfo();
        sysFileInfo1.setId(1L);
        SysFileInfo sysFileInfo2 = new SysFileInfo();
        sysFileInfo2.setId(sysFileInfo1.getId());
        assertThat(sysFileInfo1).isEqualTo(sysFileInfo2);
        sysFileInfo2.setId(2L);
        assertThat(sysFileInfo1).isNotEqualTo(sysFileInfo2);
        sysFileInfo1.setId(null);
        assertThat(sysFileInfo1).isNotEqualTo(sysFileInfo2);
    }
}
