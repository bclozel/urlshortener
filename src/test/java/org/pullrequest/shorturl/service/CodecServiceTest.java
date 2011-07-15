package org.pullrequest.shorturl.service;

import org.junit.Assert;
import org.junit.Test;

public class CodecServiceTest {
    
    @Test
    public void testEncode() {

        long uid = 1;
        String hash = CodecService.encode(uid);
        Assert.assertEquals("1", hash);
        
        uid = 63;
        hash = CodecService.encode(uid);
        Assert.assertEquals("=", hash);
        
        uid = 64;
        hash = CodecService.encode(uid);
        Assert.assertEquals("10", hash);
        
        uid = CodecService.MAX_UID-1;
        hash = CodecService.encode(uid);
        Assert.assertEquals("======", hash);
    }
    
        @Test
    public void testDecode() {

        String hash = "1";
        long uid = 1;
        uid = CodecService.decode(hash);
        Assert.assertEquals(1, uid);
        
        hash = "=";
        uid = CodecService.decode(hash);
        Assert.assertEquals(63, uid);
 
        hash = "10";
        uid = CodecService.decode(hash);
        Assert.assertEquals(64, uid);
        
        hash = "======";
        uid = CodecService.decode(hash);
        Assert.assertEquals(CodecService.MAX_UID-1, uid);
        
    }
    
    @Test
    public void testRandomUID() {

        long firstuid = CodecService.generateUID();
        long seconduid = CodecService.generateUID();
        
        Assert.assertTrue("generator should create random uids", firstuid != seconduid);
    }
    
    @Test
    public void testBijective() {

        long uid = CodecService.generateUID();

        String hash = CodecService.encode(uid);
        
        long decodedUid = CodecService.decode(hash);
        
        Assert.assertTrue("encoded and decoded UID should be the same", uid == decodedUid);
    }

    @Test
    public void testValidUID() {

        long sampleUID = CodecService.generateUID();
        Assert.assertTrue("UID should be < "+CodecService.MAX_UID, sampleUID < CodecService.MAX_UID);
        Assert.assertTrue("UID should be > 0", sampleUID > 0);
    }
}
