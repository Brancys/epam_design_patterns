package com.epam.rd.autocode.observer.git;

import java.util.*;

public class RepositoryImpl implements Repository {
    private final Map<String, List<Commit>> branches = new HashMap<>();
    private final List<WebHook> webHooks = new ArrayList<>();

    @Override
    public void addWebHook(WebHook webHook) {
        webHooks.add(webHook);
    }

    @Override
    public Commit commit(String branch, String author, String[] changes) {
        Commit newCommit = new Commit(author, changes);
        branches.computeIfAbsent(branch, k -> new ArrayList<>()).add(newCommit);
        notifyWebHooks(new Event(Event.Type.COMMIT, branch, Collections.singletonList(newCommit)));
        return newCommit;
    }

    @Override
    public void merge(String sourceBranch, String targetBranch) {
        if (!branches.containsKey(sourceBranch) || branches.get(sourceBranch).isEmpty()) {
            return;
        }

        List<Commit> sourceCommits = new ArrayList<>(branches.get(sourceBranch));

        // Asegurar que los commits no se dupliquen en el branch de destino
        List<Commit> targetCommits = branches.computeIfAbsent(targetBranch, k -> new ArrayList<>());
        Set<Commit> existingCommits = new HashSet<>(targetCommits);
        List<Commit> newCommits = new ArrayList<>();

        for (Commit commit : sourceCommits) {
            if (!existingCommits.contains(commit)) {
                newCommits.add(commit);
                targetCommits.add(commit);
            }
        }

        // Notificar solo si hay nuevos commits en el merge
        if (!newCommits.isEmpty()) {
            notifyWebHooks(new Event(Event.Type.MERGE, targetBranch, newCommits));
        }
    }

    private void notifyWebHooks(Event event) {
        for (WebHook webHook : webHooks) {
            if (webHook.type() == event.type() && webHook.branch().equals(event.branch())) {
                webHook.onEvent(event);
            }
        }
    }
}
