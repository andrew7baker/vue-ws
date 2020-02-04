package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class SysOperationLogTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysOperationLog.class);
        SysOperationLog sysOperationLog1 = new SysOperationLog();
        sysOperationLog1.setId(1L);
        SysOperationLog sysOperationLog2 = new SysOperationLog();
        sysOperationLog2.setId(sysOperationLog1.getId());
        assertThat(sysOperationLog1).isEqualTo(sysOperationLog2);
        sysOperationLog2.setId(2L);
        assertThat(sysOperationLog1).isNotEqualTo(sysOperationLog2);
        sysOperationLog1.setId(null);
        assertThat(sysOperationLog1).isNotEqualTo(sysOperationLog2);
    }
}
