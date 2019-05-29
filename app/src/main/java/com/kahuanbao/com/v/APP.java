package com.kahuanbao.com.v;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by Administrator on 2019/2/12.
 *
 */

public class APP extends TinkerApplication {
    public APP() {
        super(ShareConstants.TINKER_ENABLE_ALL,
                "com.kahuanbao.com.v.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
