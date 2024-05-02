package itusolar.controller;

import java.lang.reflect.Method;

public class HUrlMapping {
    Method method;
    HPost post;

    public HUrlMapping(Method method, HPost post) {
        this.setMethod(method);
        this.setPost(post);
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public HPost getPost() {
        return post;
    }

    public void setPost(HPost post) {
        this.post = post;
    }
}
