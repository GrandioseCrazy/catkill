package com.avaj.ekill.exception;

import com.avaj.ekill.result.CodeMsg;

public class GlobalException extends RuntimeException {
    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;
    }
    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
