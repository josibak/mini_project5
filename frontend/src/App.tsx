
import { Toaster } from "@/components/ui/toaster";
import { Toaster as Sonner } from "@/components/ui/sonner";
import { TooltipProvider } from "@/components/ui/tooltip";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Index from "./pages/Index";
import Collection from "./pages/Collection";
import Admin from "./pages/Admin";
import Author from "./pages/Author";
import AuthorRegistration from "./pages/AuthorRegistration";
import MyBooks from "./pages/MyBooks";
import AuthorSubmission from "./pages/AuthorSubmission";
import Subscription from "./pages/Subscription";
import BookDetail from "./pages/BookDetail";
import MyPage from "./pages/MyPage";
import WritingEditor from "./pages/WritingEditor";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import NotFound from "./pages/NotFound";

const queryClient = new QueryClient();

const App = () => (
  <QueryClientProvider client={queryClient}>
    <TooltipProvider>
      <Toaster />
      <Sonner />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Index />} />
          <Route path="/collection" element={<Collection />} />
          <Route path="/admin" element={<Admin />} />
          <Route path="/author" element={<Author />} />
          <Route path="/author-registration" element={<AuthorRegistration />} />
          <Route path="/my-books" element={<MyBooks />} />
          <Route path="/author-submission" element={<AuthorSubmission />} />
          <Route path="/subscription" element={<Subscription />} />
          <Route path="/book/:id" element={<BookDetail />} />
          <Route path="/my-page" element={<MyPage />} />
          <Route path="/writing-editor" element={<WritingEditor />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>
    </TooltipProvider>
  </QueryClientProvider>
);

export default App;
