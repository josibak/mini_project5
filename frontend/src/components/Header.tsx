
import { useState } from 'react';
import { Menu, X, BookOpen } from 'lucide-react';
import { Link, useLocation } from 'react-router-dom';
import UserDropdown from './UserDropdown';
import AuthorDropdown from './AuthorDropdown';

const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false); // Simple login state
  const location = useLocation();

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
  };

  const isActive = (path: string) => location.pathname === path;

  return (
    <header className="fixed top-0 left-0 right-0 z-50 bg-white/95 backdrop-blur-md border-b border-warm-brown-200">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          <Link to="/" className="flex items-center gap-2">
            <BookOpen className="w-6 h-6 text-warm-brown-700" />
            <span className="text-xl font-bold text-warm-brown-800">StoryBloom</span>
          </Link>
          
          {/* Desktop Navigation */}
          <nav className="hidden md:flex space-x-8">
            <Link 
              to="/" 
              className={`transition-colors duration-200 ${
                isActive('/') ? 'text-warm-brown-700 font-medium' : 'text-gray-700 hover:text-gray-900'
              }`}
            >
              HOME
            </Link>
            <Link 
              to="/collection" 
              className={`transition-colors duration-200 ${
                isActive('/collection') ? 'text-warm-brown-700 font-medium' : 'text-gray-700 hover:text-gray-900'
              }`}
            >
              COLLECTION
            </Link>
            <Link 
              to="/admin" 
              className={`transition-colors duration-200 ${
                isActive('/admin') ? 'text-warm-brown-700 font-medium' : 'text-gray-700 hover:text-gray-900'
              }`}
            >
              ADMIN
            </Link>
            <AuthorDropdown />
          </nav>

          <div className="hidden md:flex items-center gap-6">
            {isLoggedIn ? (
              // Show profile and logout when logged in
              <>
                <UserDropdown />
                <button 
                  onClick={handleLogout}
                  className="text-gray-600 hover:text-gray-900 transition-colors"
                >
                  로그아웃
                </button>
              </>
            ) : (
              // Show login and signup when not logged in
              <>
                <Link 
                  to="/login" 
                  className="text-gray-600 hover:text-gray-900 transition-colors"
                  onClick={() => setIsLoggedIn(true)} // Simple login simulation
                >
                  로그인
                </Link>
                <Link to="/signup" className="bg-warm-brown-700 hover:bg-warm-brown-800 text-white px-4 py-2 rounded-lg transition-colors">
                  회원가입
                </Link>
              </>
            )}
          </div>

          {/* Mobile menu button */}
          <div className="md:hidden">
            <button
              onClick={toggleMenu}
              className="text-gray-700 hover:text-gray-900 transition-colors duration-200"
            >
              {isMenuOpen ? <X size={24} /> : <Menu size={24} />}
            </button>
          </div>
        </div>

        {/* Mobile Navigation */}
        {isMenuOpen && (
          <div className="md:hidden border-t border-warm-brown-200 py-4">
            <nav className="flex flex-col space-y-4">
              <Link 
                to="/" 
                className="text-gray-700 hover:text-gray-900 transition-colors duration-200"
                onClick={() => setIsMenuOpen(false)}
              >
                HOME
              </Link>
              <Link 
                to="/collection" 
                className="text-gray-700 hover:text-gray-900 transition-colors duration-200"
                onClick={() => setIsMenuOpen(false)}
              >
                COLLECTION
              </Link>
              <Link 
                to="/admin" 
                className="text-gray-700 hover:text-gray-900 transition-colors duration-200"
                onClick={() => setIsMenuOpen(false)}
              >
                ADMIN
              </Link>
              <Link 
                to="/my-books" 
                className="text-gray-700 hover:text-gray-900 transition-colors duration-200"
                onClick={() => setIsMenuOpen(false)}
              >
                나의 글 관리
              </Link>
              <Link 
                to="/author-submission" 
                className="text-gray-700 hover:text-gray-900 transition-colors duration-200"
                onClick={() => setIsMenuOpen(false)}
              >
                새 글 작성하기
              </Link>
              <Link 
                to="/author-registration" 
                className="text-gray-700 hover:text-gray-900 transition-colors duration-200"
                onClick={() => setIsMenuOpen(false)}
              >
                작가 등록 신청
              </Link>
            </nav>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;
