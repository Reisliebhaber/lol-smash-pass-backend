//package com.example.security;
//
//import com.example.entities.User;
//
////import org.jboss.resteasy.core.ResourceMethodInvoker;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import javax.annotation.security.PermitAll;
//import javax.transaction.Transactional;
//import javax.ws.rs.ForbiddenException;
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerRequestFilter;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.ext.Provider;
//
//@Provider
//public class SecurityInterceptor implements ContainerRequestFilter {
//
//    @Override
//    @Transactional
//    public void filter(ContainerRequestContext requestContext) throws IOException {
//        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
//        Method method = methodInvoker.getMethod();
//        if (method.getDeclaringClass().isAnnotationPresent(PermitAll.class) || method.isAnnotationPresent(PermitAll.class)) {
//            return;
//        }
//        if (requestContext.getSecurityContext().getUserPrincipal() != null) {
//
//            User currentUser = (User) User.find("email", requestContext.getSecurityContext().getUserPrincipal().getName()).singleResultOptional().orElseThrow(ForbiddenException::new);
//
//            ArrayList<PermitRole> permitRoles = new ArrayList<>();
//            permitRoles.addAll(Arrays.asList(method.getAnnotationsByType(PermitRole.class)));
//            permitRoles.addAll(Arrays.asList(method.getDeclaringClass().getAnnotationsByType(PermitRole.class)));
//            if (permitRoles.stream().anyMatch(permitRole -> permitRole.value() == currentUser.role)) {
//                return;
//            }
//
//            if (method.isAnnotationPresent(PermitUser.class)) {
//                String userIdPathParam = method.getAnnotation(PermitUser.class).pathParam();
//                String userId = requestContext.getUriInfo().getPathParameters().getFirst(userIdPathParam);
//                if (currentUser.id.equals(Long.parseLong(userId))) {
//                    return;
//                }
//            }
//
//        }
//        requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
//    }
//}
